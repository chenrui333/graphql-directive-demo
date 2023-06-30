package com.github.chenrui333.model;

import java.util.Objects;

public class Rating {

  private String title;
  private Integer stars;

  public Rating(String title, Integer stars) {
    this.title = title;
    this.stars = stars;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Integer getStars() {
    return stars;
  }

  public void setStars(Integer stars) {
    this.stars = stars;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Rating rating = (Rating) o;
    return Objects.equals(title, rating.title) && Objects.equals(stars, rating.stars);
  }

  @Override
  public int hashCode() {
    return Objects.hash(title, stars);
  }
}
