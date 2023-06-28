package com.github.chenrui333.model;

import java.util.Objects;

public class Rating {
  private Integer stars;

  public Rating() {
  }

  public Rating(Integer stars) {
    this.stars = stars;
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
    return Objects.equals(stars, rating.stars);
  }

  @Override
  public int hashCode() {
    return Objects.hash(stars);
  }
}
