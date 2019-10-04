package social.media.saree.saree;

public class saree {
    public String saree_Id;
    public String saree_Name;
    public String saree_Label;
    public String saree_Price;
    public String saree_Material;
    public String saree_Length;
    public String saree_Occations;
    public String saree_Color;
    public String saree_custom_a;
    public String saree_custom_b;
    public String saree_custom_c;
    public String saree_rating;
    public String saree_Description;
    public String saree_style;
    public String saree_photo;

    public saree(){

    }
    public saree(String saree_Id,String saree_Name,String saree_Label,String saree_Price,String saree_Material, String saree_Length, String saree_Occations, String saree_color, String saree_Rating, String saree_Description, String saree_style, String saree_photo,  String saree_custom_a, String saree_custom_b, String saree_custom_c){
        this.saree_Id = saree_Id;
        this.saree_Name = saree_Name;
        this.saree_Label = saree_Label;
        this.saree_Price = saree_Price;
        this.saree_Material = saree_Material;
        this.saree_Length = saree_Length;
        this.saree_Occations = saree_Occations;
        this.saree_Color = saree_color;
        this.saree_custom_a = saree_custom_a;
        this.saree_custom_b = saree_custom_b;
        this.saree_custom_c = saree_custom_c;
        this.saree_rating = saree_Rating;
        this.saree_Description = saree_Description;
        this.saree_style = saree_style;
    }

    public String getSaree_Color() {
        return saree_Color;
    }

    public String getSaree_custom_a() {
        return saree_custom_a;
    }

    public String getSaree_custom_b() {
        return saree_custom_b;
    }

    public String getSaree_custom_c() {
        return saree_custom_c;
    }

    public String getSaree_Label() {
        return saree_Label;
    }

    public String getSaree_Length() {
        return saree_Length;
    }

    public String getSaree_Name() {
        return saree_Name;
    }

    public String getSaree_Occations() {
        return saree_Occations;
    }

    public String getSaree_Price() {
        return saree_Price;
    }

    public String getSaree_Material() {
        return saree_Material;
    }

    public String getSaree_Description() {
        return saree_Description;
    }

    public String getSaree_Id() {
        return saree_Id;
    }

    public String getSaree_rating() {
        return saree_rating;
    }

    public String getSaree_style() {
        return saree_style;
    }

    public String getSaree_photo() {
        return saree_photo;
    }
}
