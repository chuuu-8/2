package mypos;

import java.util.TreeMap;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
import models.OrderDetail;
import models.Product;
import models.ReadCategoryProduct;

public class AppPosVerticle extends Application {

    private TilePane menuSEVENTEEN = getProductCategoryMenu("SEVENTEEN");
    TilePane menuBTS = getProductCategoryMenu("BTS");
    TilePane menuTXT = getProductCategoryMenu("TXT");

    private ObservableList<OrderDetail> order_list;

    private TableView<OrderDetail> table;

    private final TreeMap<String, Product> product_dict = ReadCategoryProduct.readProduct();

    private final TextArea display = new TextArea();

    public TilePane getProductCategoryMenu(String category) {

        TreeMap<String, Product> product_dict = ReadCategoryProduct.readProduct();
        TilePane category_menu = new TilePane();
        category_menu.setVgap(10);
        category_menu.setHgap(10);
        category_menu.setPrefColumns(4);

        for (String item_id : product_dict.keySet()) {
            if (product_dict.get(item_id).getCategory().equals(category)) {
                Button btn = new Button();

                btn.setPrefSize(120, 120);

                Image img = new Image("/imgs/" + product_dict.get(item_id).getPhoto());
                ImageView imgview = new ImageView(img);
                imgview.setFitHeight(80);
                imgview.setPreserveRatio(true);

                btn.setGraphic(imgview);
                category_menu.getChildren().add(btn);

                btn.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        addToCart(item_id);
                        System.out.println(product_dict.get(item_id).getName());
                    }
                });
            }
        }
        return category_menu;
    }

    VBox menuContainerPane = new VBox();

    public TilePane getMenuSelectionContainer() {

        
        Button btnSEVENTEEN = new Button();
        btnSEVENTEEN.setText("SEVENTEEN");
        btnSEVENTEEN.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                menuContainerPane.getChildren().clear();
                menuContainerPane.getChildren().add(menuSEVENTEEN);
            }
        });
        
        Button btnBTS = new Button("BTS");
        btnBTS.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                select_category_menu(e);
            }
        });
        
        Button btnTXT = new Button("TXT");
        btnTXT.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                select_category_menu(e);
            }
        });

        TilePane conntainerCategoryMenuBtn = new TilePane();
        conntainerCategoryMenuBtn.setVgap(10);
        conntainerCategoryMenuBtn.setHgap(10);

        conntainerCategoryMenuBtn.getChildren().add(btnSEVENTEEN);
        conntainerCategoryMenuBtn.getChildren().add(btnBTS);
        conntainerCategoryMenuBtn.getChildren().add(btnTXT);

        return conntainerCategoryMenuBtn;
    } 

    public void select_category_menu(ActionEvent event) {
        String category = ((Button) event.getSource()).getText();
        menuContainerPane.getChildren().clear();
        switch (category) {
            case "SEVENTEEN":
                menuContainerPane.getChildren().add(menuSEVENTEEN);
                break;
            case "BTS":
                menuContainerPane.getChildren().add(menuBTS);
                break;
            case "TXT":
                menuContainerPane.getChildren().add(menuTXT);
                break;
            default:
                break;
        }

    }

    public TilePane getOrderOperationContainer() {

        Button btnAdd = new Button();
        btnAdd.setText("新增一筆專輯");
        btnAdd.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("新增一筆");
                addToCart("p-j-101");
            }
        });

        Button btnDelete = new Button("刪除一筆");
        btnDelete.setOnAction((ActionEvent e) -> {
            OrderDetail selectedItem = table.getSelectionModel().getSelectedItem();
            order_list.remove(selectedItem);
            checkTotal();
            System.out.println("執行刪除訂單!");
            System.out.println(selectedItem.getProduct_name());
        });

        TilePane operationBtnTile = new TilePane();
        operationBtnTile.setVgap(10);
        operationBtnTile.setHgap(10);

        operationBtnTile.getChildren().add(btnAdd);
        operationBtnTile.getChildren().add(btnDelete);

        return operationBtnTile;
    }

    public void initializeOrderTable() {

        order_list = FXCollections.observableArrayList();
        order_list = FXCollections.observableArrayList(
                new OrderDetail("p-101", "1st Mini Album '17 CARAT'", 328, 1),
                new OrderDetail("p-102", "2nd Mini Album 'BOYS BE'", 378, 1)
        );

        order_list.add(new OrderDetail("p-103", "1st ALBUM [FIRST 'LOVE & LETTER']", 528, 2));
        checkTotal();

        table = new TableView<>();
        table.setEditable(true);
        table.setPrefHeight(300);

        TableColumn<OrderDetail, String> order_item_name = new TableColumn<>("專輯名");
        order_item_name.setCellValueFactory(new PropertyValueFactory("product_name"));
        order_item_name.setCellFactory(TextFieldTableCell.forTableColumn());
        order_item_name.setPrefWidth(100);
        order_item_name.setMinWidth(100);

        TableColumn<OrderDetail, Integer> order_item_price = new TableColumn<>("價格");
        order_item_price.setCellValueFactory(new PropertyValueFactory("product_price"));

        TableColumn<OrderDetail, Integer> order_item_qty = new TableColumn<>("數量");
        order_item_qty.setCellValueFactory(new PropertyValueFactory("quantity"));

        order_item_qty.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

        order_item_qty.setOnEditCommit(event -> {

            int row_num = event.getTablePosition().getRow();
            int new_val = event.getNewValue();
            OrderDetail target = event.getTableView().getItems().get(row_num);
            target.setQuantity(new_val);

            System.out.println(order_list.get(row_num).getProduct_name()+"被修改數量");
            System.out.println("數量修改為:" + order_list.get(row_num).getQuantity());
        });

        table.setItems(order_list);

        table.getItems().add(new OrderDetail("p-104", "Love&Letter Repackage Album", 538, 2));
        checkTotal();

        table.getColumns().addAll(order_item_name, order_item_price, order_item_qty);

        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

    }

    public void checkTotal() {
        double total = 0;
        for (OrderDetail od : order_list) {
            total += od.getProduct_price() * od.getQuantity();
        }

        String totalmsg = String.format("%s %d\n", "總金額:", Math.round(total));
        display.setText(totalmsg);
    }

    public void addToCart(String item_id) {

        boolean duplication = false;
        for (int i = 0; i < order_list.size(); i++) {
            if (order_list.get(i).getProduct_id().equals(item_id)) {
                int qty = order_list.get(i).getQuantity() + 1;
                order_list.get(i).setQuantity(qty);
                duplication = true;
                table.refresh();
                checkTotal();
                System.out.println(item_id + "此筆已經加入購物車，數量+1");
                break;
            }
        }
        if (!duplication) {
            OrderDetail new_ord = new OrderDetail(
                    item_id,
                    product_dict.get(item_id).getName(),
                    product_dict.get(item_id).getPrice(),
                    1);
            order_list.add(new_ord);
            System.out.println(item_id);

            checkTotal();
        }
    }

    @Override
    public void start(Stage stage) {
        VBox root = new VBox();
        root.setSpacing(10);
        root.setPadding(new Insets(10, 10, 10, 10));
        root.getStylesheets().add("/css/bootstrap3.css");

        TilePane menuSelectionTile = getMenuSelectionContainer();
        root.getChildren().add(menuSelectionTile);

        menuContainerPane.getChildren().add(menuSEVENTEEN);

        root.getChildren().add(menuContainerPane);

        root.getChildren().add(getOrderOperationContainer());

        initializeOrderTable();
        root.getChildren().add(table);

        display.setPrefColumnCount(10);
        root.getChildren().add(display);

        Scene scene = new Scene(root);
        stage.setTitle("專輯目錄");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

}
