package com.pubgdemo.pubgdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableScheduling
public class PubgDemoApplication {

	/**
	 * 
	 * Method called on start up 
	 * Spring boot application
	 */
    public static void main(String[] args) {
        SpringApplication.run(PubgDemoApplication.class, args);
    }
}


    /**
     * 
     * Controller to expose endoints for API to 
     * get player data based on accountId
     *
     */
	@RestController
	class LeaderboardController {
        
		//minio service URL
	    @Value("${minio.url}")
	    private String minioUrl;

	    //Redis service host ip
	    @Value("${redis.host}")
	    private String redisHost;

	    //redis service port
	    @Value("${redis.port}")
	    private int redisPort;

	    /**
	     * 
	     * Scheduer service for get latest leaderboard data from
	     *  PUBG Leaderboard API
	     *  Scheduler runs daily at 00:00
	     * 
	     */
	    @Scheduled(cron = "0 0 0 * * *") // Run daily
	    public void fetchAndStoreLeaderboardData() {
	        // Fetch leaderboard data from PUBG API
	        String leaderboardData = fetchLeaderboardData();

	        // Store data in MinIO
	        storeDataInMinio(leaderboardData);

	        // Store data in Redis
	        storeDataInRedis(leaderboardData);
	    }

	    /**
	     * 
	     *Method to make REST call to PUBG leaderboard API
	     *Return Leaderboard data 
	     */
	    private String fetchLeaderboardData() {
	        RestTemplate restTemplate = new RestTemplate();
	        String apiUrl = "https://api.pubg.com/leaderboards";
	        return restTemplate.getForObject(apiUrl, String.class);
	    }

	    /**
	     * 
	     * Method to store LeaderBoard API data into minio
	     * 
	     */
	    private void storeDataInMinio(String data) {
	        // Implement storing data in MinIO
	        // Example: use Minio Java SDK
	    }
	    
	    /**
	     * 
	     * Method to process Leaderboard data basd on accountId and store in redis
	     * 	     * 
	     */
	    private void storeDataInRedis(String data) {
	        // Implement storing data in Redis
	        // Example: use Jedis or Lettuce Redis client
	    }

	    /**
	     * 
	     * Get API Endpoint to get Leaderboard data based on player accountId
	     * @return
	     */
	    @GetMapping("/leaderboard/{accountId}")
	    public String getLeaderboardData(@PathVariable String accountId) {
	        // Retrieve data from Redis using account ID
	        // Example: use Jedis or Lettuce Redis client to retrieve data by key
	        return "Leaderboard data for account ID " + accountId;
	    }}

}
