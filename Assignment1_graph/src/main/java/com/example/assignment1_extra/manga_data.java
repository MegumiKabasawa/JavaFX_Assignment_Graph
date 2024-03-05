package com.example.assignment1_extra;

public class manga_data {

    private int rank_table;
    private String title_table;
    private int sales_table;

    //Constructor
    public manga_data(int rank_table,String title_table,int sales_table){
        this.rank_table=rank_table;
        this.title_table = title_table;
        this.sales_table = sales_table;
    }

    //Getter
    public int getRank_table() {
        return rank_table;
    }

    //Setter
    public void setRank_table(int rank_table) {
        this.rank_table = rank_table;
    }
    //Getter
    public String getTitle_table() {
        return title_table;
    }

    //Setter
    public void setTitle_table(String title_table) {
        this.title_table = title_table;
    }

    //Getter
    public int getSales_table() {
        return sales_table;
    }

    //Setter
    public void setSales_table(int sales_table) {
        this.sales_table = sales_table;
    }
}
