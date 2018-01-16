package com.greglturnquist.payroll.emails;

import com.greglturnquist.payroll.recipes.Ingredient;

import java.util.List;

/**
 * Created by Paulina on 16.01.2018.
 */
public class ShoppingListSender implements EmailParser {

    private String lunchName;
    private List<Ingredient> ingredients;

    public ShoppingListSender(String lunchName, List<Ingredient> ingredients) {
        this.lunchName = lunchName;
        this.ingredients = ingredients;
    }

    @Override
    public String getEmailTitle() {
        return lunchName + " - shopping list";
    }

    @Override
    public String getEmailBody() {

        StringBuilder shoppingList = new StringBuilder("<ul>\n");

        ingredients.forEach(ingredient -> shoppingList.append("<li><p>")
                .append(ingredient.toString())
                .append("</p></li>\n"));

        shoppingList.append("</ul>\n");

        return "<!doctype html>\n" +
                "<html>\n" +
                "  <head>\n" +
                "    <meta name=\"viewport\" content=\"width=device-width\" />\n" +
                "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\n" +
                "    <link rel=\"stylesheet\" type=\"text/css\" href=\"http://fonts.googleapis.com/css?family=Cabin+Sketch\" />\n" +
                "    <style>\n" +
                "\n" +
                "      /* -------------------------------------\n" +
                "          BODY & CONTAINER\n" +
                "      ------------------------------------- */\n" +
                ".container {\n" +
                " \n" +
                "    margin:10px;\n" +
                "    float:left;\n" +
                "}\n" +
                ".left-item, .right-item {\n" +
                "\tfloat:left;\n" +
                "    margin:5px;\n" +
                "}\n" +
                ".right-item {\n" +
                "\tmargin-right:40px;\n" +
                "\ttext-align: center;\n" +
                "}\n" +
                "\n" +
                "img {\n" +
                "\theight:200px;\n" +
                "\t\n" +
                "}\n" +
                "ul { \n" +
                "\tlist-style-type : none;\n" +
                "\tpadding: 0;\n" +
                "\tmargin: 0;\n" +
                "}\n" +
                "\n" +
                ".regards{\n" +
                "\tvertical-align: bottom;\n" +
                "\tmargin-top: 30px;\n" +
                "}\n" +
                "      /* -------------------------------------\n" +
                "          TYPOGRAPHY\n" +
                "      ------------------------------------- */\n" +
                "h3 {\n" +
                "\tfont-family: \"Cabin Sketch\";\n" +
                "\tfont-size: 17px;\n" +
                "\tfont-style: normal;\n" +
                "\tfont-variant: normal;\n" +
                "\tfont-weight: 500;\n" +
                "\tline-height: 23px;\n" +
                "}\n" +
                "p {\n" +
                "\tfont-family: \"Cabin Sketch\";\n" +
                "\tfont-size: 14px;\n" +
                "\tfont-style: normal;\n" +
                "\tfont-variant: normal;\n" +
                "\tfont-weight: 400;\n" +
                "}\n" +
                "      /* -------------------------------------\n" +
                "          BUTTONS\n" +
                "      ------------------------------------- */\n" +
                "\t  \n" +
                "    </style>\n" +
                "  </head>\n" +
                "  <body class=\"\">\n" +
                "\t<div class=\"container\"> \n" +
                "\t\t<div class=\"left-item\">\n" +
                "\t\t<img src=\"https://cdn.pixabay.com/photo/2016/06/29/16/19/potato-1487166_1280.png\" />\n" +
                "\t\t</div>\n" +
                "\t\t<div class=\"right-item\">\n" +
                "\t\t<h3> Hi, you have to buy below ingredients: </h3>\n" +
                shoppingList.toString() +
                "<div class=\"regards\"> \n" +
                "\t\t<p>Best regards, </br> LunchRouletteTeam </p>\n" +
                "\t\t</div>\n" +
                "\t\t</div>\n" +
                "\t</div>\n" +
                "  </body>\n" +
                "</html>";

    }
}
