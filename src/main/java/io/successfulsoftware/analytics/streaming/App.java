package io.successfulsoftware.analytics.streaming;

public class App {
	
	 public static void main(String[] args) throws Exception {
		 work.task(getKafkaURI(), getZookeeperURI(), 2, "", "");
	 }
	 
	 private static String getKafkaURI() {
	        String kafkaURL= "localhost:9092";
	        try {
	            String env_uri=System.getenv("KAFKA_URI");
	        if(env_uri !=null )
	            kafkaURL=env_uri;
	        }catch (Exception ex){
	            System.out.println("Errror kafka url not set");
	        }
	        return kafkaURL;
	    }
	    private static String getZookeeperURI() {
	        String url= "localhost:2181";
	        try {
	            String env_uri = System.getenv("ZOOKEEPER_URI");
	            if (env_uri != null)
	                url = env_uri;
	        }catch (Exception ex){
	            System.out.println("Errror: zookeeper env not set");
	        }

	        return url;
	    }

}
