package com.example.itime;

public class ChooseItem {
    private String Title;
    private String Description;
    int ImageId;

    public ChooseItem(String title, String description, int imageId) {
        Title = title;
        Description = description;
        ImageId = imageId;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getImageId() {
        return ImageId;
    }

    public void setImageId(int ImageId) {
        this.ImageId = ImageId;
    }
}

