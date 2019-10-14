package social.media.saree.util;

import java.util.ArrayList;

import social.media.saree.Member.Member;
import social.media.saree.cart.cart_item;
import social.media.saree.order.order;
import social.media.saree.saree.saree;

public class Global {
    public static String current_user_email ;
    public static String current_user_name = "";
    public static String current_user_photo;
    public static Integer current_user_index;
    public static boolean is_admin = false;

    public static ArrayList<Member> array_all_members = new ArrayList<Member>();
    public static ArrayList<saree> array_all_sarees = new ArrayList<saree>();
    public static ArrayList<saree> array_shaandar_sarees = new ArrayList<saree>();
    public static ArrayList<saree> array_Khoobsurat_sarees = new ArrayList<saree>();
    public static ArrayList<saree> array_Dhamakedar_sarees = new ArrayList<saree>();
    public static ArrayList<order> array_all_order = new ArrayList<order>();

    public static saree selected_saree;
    public static ArrayList<cart_item> user_carts = new ArrayList<cart_item>();
    public static Integer cart_total_price = 0;
}
