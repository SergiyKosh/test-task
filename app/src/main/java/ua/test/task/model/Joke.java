package ua.test.task.model;

import com.google.gson.annotations.SerializedName;

import java.util.Arrays;
import java.util.Objects;

public class Joke {
    @SerializedName("categories")
    private String[] categories;
    @SerializedName("created_at")
    private String createdAt;
    @SerializedName("icon_url")
    private String iconUrl;
    @SerializedName("id")
    private String id;
    @SerializedName("updated_at")
    private String updatedAt;
    @SerializedName("url")
    private String url;
    @SerializedName("value")
    private String value;

    public Joke(String[] categories, String createdAt, String iconUrl, String id, String updatedAt, String url, String value) {
        this.categories = categories;
        this.createdAt = createdAt;
        this.iconUrl = iconUrl;
        this.id = id;
        this.updatedAt = updatedAt;
        this.url = url;
        this.value = value;
    }

    public String[] getCategories() {
        return categories;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public String getUrl() {
        return url;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Joke joke = (Joke) o;
        return Arrays.equals(categories, joke.categories) && Objects.equals(createdAt, joke.createdAt) && Objects.equals(iconUrl, joke.iconUrl) && Objects.equals(id, joke.id) && Objects.equals(updatedAt, joke.updatedAt) && Objects.equals(url, joke.url) && Objects.equals(value, joke.value);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(createdAt, iconUrl, id, updatedAt, url, value);
        result = 31 * result + Arrays.hashCode(categories);
        return result;
    }
}
