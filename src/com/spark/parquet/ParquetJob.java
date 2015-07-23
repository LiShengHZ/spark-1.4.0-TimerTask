package com.spark.parquet;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SQLContext;

public class ParquetJob {
	private static JavaSparkContext sc;
	private static SQLContext sqlContext;
	private static final long PERIOD_DAY = 24 * 60 * 60 * 1000;
	private static final Pattern pattern = Pattern.compile("<([^>]*)>=([^<]*)");

	public static void main(String[] args) {
		System.setProperty("spark.executor.memory", "2g");
		final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
		TimerTask task = new TimerTask() {
			public void run() {
				sc = new JavaSparkContext("spark://trsdc-01:7077", "LogsEscapeSparkJob");
				sc.addJar("file:///home/trsdc/apps/spark-1.4.0-bin-hadoop2.4/lib/LogsEscapeSparkJob.jar");
				sqlContext = new org.apache.spark.sql.SQLContext(sc);
				//转换昨天日志
				Calendar calendar = Calendar.getInstance();
				calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - 1);
				String suffix = simpleDateFormat.format(calendar.getTime());
				escapeParquet("hdfs://trsdc-01:9000/logs/trsserver/trsserver"+suffix,"hdfs://trsdc-01:9000/parquet/trsserver/trsserver"+suffix,new TrsserverLog());
				escapeParquet("hdfs://trsdc-01:9000/logs/hybase/hybase"+suffix,"hdfs://trsdc-01:9000/parquet/hybase/hybase"+suffix,new HybaseLog());
				escapeParquet("hdfs://trsdc-01:9000/logs/trsserver-cluster/server-cluster"+suffix,"hdfs://trsdc-01:9000/parquet/trsserver-cluster/server-cluster"+suffix,new TrsserverClusterLog());
				escapeParquet("hdfs://trsdc-01:9000/logs/ckm/ckm"+suffix,"hdfs://trsdc-01:9000/parquet/ckm/ckm"+suffix,new CkmLog());
				sc.stop();
			}
		};
		task.run();
		
		Timer timer = new Timer();
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + 1);
		calendar.set(Calendar.HOUR_OF_DAY, 3);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		timer.schedule(task, calendar.getTime(), PERIOD_DAY);
	}
	
	private static <T> void escapeParquet(String txtPath, String patPath,final T t) {
		JavaRDD<T> bean = sc.textFile(txtPath,2).map(new Function<String, T>() {
			public T call(String line) throws Exception {
				@SuppressWarnings("unchecked")
				T bean = (T) ReflectUtil.getBean(t.getClass().getName());
				Matcher matcher = pattern.matcher(line);
				while(matcher.find()){
					try{
						ReflectUtil.invokeSet(bean, matcher.group(1), matcher.group(2));
					}catch(NoSuchFieldException e){
					}catch(Exception e){
						e.printStackTrace();
					}
				}
				return bean;
			}
		});
		DataFrame schemaPeople = sqlContext.createDataFrame(bean, t.getClass());
		try{
			schemaPeople.write().parquet(patPath);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	private static List<String> readParquet(String patPath, String sql) {
		DataFrame parquetFile = sqlContext.read().parquet(patPath);
		parquetFile.registerTempTable("parquetFile");
		DataFrame dataFrame = sqlContext.sql(sql);
		return dataFrame.javaRDD().map(new Function<Row, String>() {
			public String call(Row row) {
				return "Name: " + row.getString(0) + " Age: " + row.getInt(1);
			}
		}).collect();
	}
}
