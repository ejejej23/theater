package source;

public class MovieDTO {
	private String movieCode, title, Eng_title;
	private String director, actor, releseDate, story, movieCompany;
	private Integer playtime;
	
	private String reviewCode;
	private Integer review;
	
	private String genreCode, gradeCode;
	
	public String getMovieCode() {
		return movieCode;
	}
	public void setMovieCode(String movieCode) {
		this.movieCode = movieCode;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	public String getEng_title() {
		return Eng_title;
	}
	public void setEng_title(String eng_title) {
		Eng_title = eng_title;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public String getActor() {
		return actor;
	}
	public void setActor(String actor) {
		this.actor = actor;
	}
	public String getReleseDate() {
		return releseDate;
	}
	public void setReleseDate(String releseDate) {
		this.releseDate = releseDate;
	}
	public String getStory() {
		return story;
	}
	public void setStory(String story) {
		this.story = story;
	}
	public String getMovieCompany() {
		return movieCompany;
	}
	public void setMovieCompany(String movieCompany) {
		this.movieCompany = movieCompany;
	}
	public Integer getPlaytime() {
		return playtime;
	}
	public void setPlaytime(Integer playtime) {
		this.playtime = playtime;
	}
	public String getGenreCode() {
		return genreCode;
	}
	public void setGenreCode(String genreCode) {
		this.genreCode = genreCode;
	}
	public String getGradeCode() {
		return gradeCode;
	}
	public void setGradeCode(String gradeCode) {
		this.gradeCode = gradeCode;
	}
	
	public String getReviewCode() {
		return reviewCode;
	}
	public void setReviewCode(String reviewCode) {
		this.reviewCode = reviewCode;
	}
	public Integer getReview() {
		return review;
	}
	public void setReview(Integer review) {
		this.review = review;
	}

	
}
