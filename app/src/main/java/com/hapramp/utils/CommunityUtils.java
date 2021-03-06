package com.hapramp.utils;

import com.hapramp.R;
import com.hapramp.steem.Communities;

public class CommunityUtils {
  //images
  public static final int EXPLORE_ICON = R.drawable.explore_large_white;
  public static final int FEED_ICON = R.drawable.feed_filled;
  public static final int ART_ICON = R.drawable.art_filled;
  public static final int DANCE_ICON = R.drawable.dance_filled;
  public static final int TRAVEL_ICON = R.drawable.travel;
  public static final int LITERATURE_ICON = R.drawable.literature_filled;
  public static final int FILM_ICON = R.drawable.film_filled;
  public static final int PHOTOGRAPHY_ICON = R.drawable.photography_filled;
  public static final int MUSIC_ICON = R.drawable.music_filled;
  public static final int FASHION_ICON = R.drawable.fashion_filled;
  public static final int DESIGN_ICON = R.drawable.design_filled;
  public static final int EDIT_COMMUNITY_ICON = R.drawable.plus;
  //backgrounds
  public static final int EXPLORE_FILLED_BG = R.drawable.feed_filled_bg;
  public static final int FEED_FILLED_BG = R.drawable.feed_filled_bg;
  public static final int ART_FILLED_BG = R.drawable.art_filled_bg;
  public static final int DANCE_FILLED_BG = R.drawable.dance_filled_bg;
  public static final int TRAVEL_FILLED_BG = R.drawable.travel_filled_bg;
  public static final int LITERATURE_FILLED_BG = R.drawable.literature_filled_bg;
  public static final int FILM_FILLED_BG = R.drawable.film_filled_bg;
  public static final int PHOTOGRAPHY_FILLED_BG = R.drawable.photography_filled_bg;
  public static final int MUSIC_FILLED_BG = R.drawable.music_filled_bg;
  public static final int FASHION_FILLED_BG = R.drawable.fashion_filled_bg;
  public static final int DESIGN_FILLED_BG = R.drawable.design_filled_bg;
  //borders
  public static final int BLACK_BORDER = R.drawable.black_border;
  public static final int FEED_BORDER = R.drawable.feed_border;
  public static final int ART_BORDER = R.drawable.art_border;
  public static final int DANCE_BORDER = R.drawable.dance_border;
  public static final int TRAVEL_BORDER = R.drawable.travel_border;
  public static final int LITERATURE_BORDER = R.drawable.literature_border;
  public static final int FILM_BORDER = R.drawable.film_border;
  public static final int PHOTOGRAPHY_BORDER = R.drawable.photography_border;
  public static final int MUSIC_BORDER = R.drawable.music_border;
  public static final int FASHION_BORDER = R.drawable.fashion_border;
  public static final int DESIGN_BORDER = R.drawable.design_border;
  public static final int EDIT_BORDER = R.drawable.edit_community_border;
  //colors
  public static final String ART_COLOR = "#ffeb3b";
  public static final String DANCE_COLOR = "#9c27b0";
  public static final String TRAVEL_COLOR = "#8bc34a";
  public static final String LITERATURE_COLOR = "#607d8b";
  public static final String FILM_COLOR = "#795548";
  public static final String PHOTOGRAPHY_COLOR = "#2196f3";
  public static final String FASHION_COLOR = "#283593";
  public static final String DESIGN_COLOR = "#0097a7";

  public static int getBorder(int com_id) {
    //ignore community id for now.
    return BLACK_BORDER;
  }

  public static int getFilledBackground(String tag) {
    switch (tag) {
      case Communities.EXPLORE:
        return EXPLORE_FILLED_BG;

      case Communities.FEEDS:
        return FEED_FILLED_BG;

      case Communities.ART:
        return ART_FILLED_BG;

      case Communities.DANCE:
        return DANCE_FILLED_BG;

      case Communities.TRAVEL:
        return TRAVEL_FILLED_BG;

      case Communities.LITERATURE:
        return LITERATURE_FILLED_BG;

      case Communities.FILM:
        return FILM_FILLED_BG;

      case Communities.PHOTOGRAPHY:
        return PHOTOGRAPHY_FILLED_BG;

      case Communities.FASHION:
        return FASHION_FILLED_BG;

      case Communities.DESIGN:
        return DESIGN_FILLED_BG;

      case Communities.EDIT_BORDER:
        return EDIT_BORDER;
    }
    return -1;
  }

  public static int getCommunityIcon(String tag) {
    switch (tag) {
      case Communities.EXPLORE:
        return EXPLORE_ICON;

      case Communities.FEEDS:
        return FEED_ICON;

      case Communities.ART:
        return ART_ICON;

      case Communities.DANCE:
        return DANCE_ICON;

      case Communities.TRAVEL:
        return TRAVEL_ICON;

      case Communities.LITERATURE:
        return LITERATURE_ICON;

      case Communities.FILM:
        return FILM_ICON;

      case Communities.PHOTOGRAPHY:
        return PHOTOGRAPHY_ICON;

      case Communities.FASHION:
        return FASHION_ICON;

      case Communities.DESIGN:
        return DESIGN_ICON;

      case Communities.EDIT_BORDER:
        return EDIT_COMMUNITY_ICON;
    }
    return -1;
  }

  public static String getCommunityColorFromTitle(String title) {
    title = title.toLowerCase();
    switch (title) {

      case "art":
        return ART_COLOR;

      case "dance":
        return DANCE_COLOR;

      case "travel":
        return TRAVEL_COLOR;

      case "literature":
        return LITERATURE_COLOR;

      case "film":
        return FILM_COLOR;

      case "photography":
        return PHOTOGRAPHY_COLOR;

      case "fashion":
        return FASHION_COLOR;

      case "design":
        return DESIGN_COLOR;
    }
    return "#009988";
  }

  public static int getCommunityIdFromTitle(String tag) {
    tag = tag.toLowerCase().replace("hapramp-", "");
    switch (tag) {

      case "art":
        return CommunityIds.ART;

      case "dance":
        return CommunityIds.DANCE;

      case "travel":
        return CommunityIds.TRAVEL;

      case "literature":
        return CommunityIds.LITERATURE;

      case "film":
        return CommunityIds.FILM;

      case "photography":
        return CommunityIds.PHOTOGRAPHY;

      case "fashion":
        return CommunityIds.FASHION;

      case "design":
        return CommunityIds.DESIGN;
    }
    return -1;
  }

  public static String getCommunityTitleFromTag(String name) {
    return name.replace("hapramp-", "");
  }
}
