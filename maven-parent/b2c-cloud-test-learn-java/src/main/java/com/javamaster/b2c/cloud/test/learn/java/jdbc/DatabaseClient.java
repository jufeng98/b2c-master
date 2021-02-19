package com.javamaster.b2c.cloud.test.learn.java.jdbc;

import com.javamaster.b2c.cloud.test.learn.java.jdbc.impl.MysqlDatabaseServiceImpl;
import com.javamaster.b2c.cloud.test.learn.java.jdbc.model.Column;
import com.javamaster.b2c.cloud.test.learn.java.jdbc.model.Database;
import com.javamaster.b2c.cloud.test.learn.java.jdbc.model.Table;
import com.javamaster.b2c.cloud.test.learn.java.utils.MybatisUtils;
import com.sun.glass.ui.Screen;
import com.sun.javafx.tk.Toolkit;
import javafx.application.Application;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lombok.SneakyThrows;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

/**
 * @author yudong
 * @date 2019/7/8
 */
public class DatabaseClient extends Application {
    public static final String TABLES = "Tables";
    public static final String VIEWS = "Views";

    private DatabaseService databaseService;

    private TabPane tabPane;

    @Override
    public void start(Stage primaryStage) {
        databaseService = new MysqlDatabaseServiceImpl(MybatisUtils.getDataSourceJdbcOnlineExam());
        primaryStage.setTitle("database management client");

        VBox stageVBox = new VBox();

        MenuBar menuBar = new MenuBar();
        stageVBox.getChildren().add(menuBar);

        Menu dbMenu = new Menu("database");
        menuBar.getMenus().add(dbMenu);
        MenuItem menuItem = new MenuItem("show database info");
        dbMenu.getItems().add(menuItem);
        menuItem.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            @Override
            @SneakyThrows
            public void handle(ActionEvent event) {
                Database database = databaseService.getDatabase();
                Field[] fields = database.getClass().getDeclaredFields();
                AccessibleObject.setAccessible(fields, true);
                VBox vBox = new VBox();
                for (Field field : fields) {
                    Label label = new Label(field.getName() + ":" + field.get(database));
                    vBox.getChildren().add(label);
                }
                Stage stage = new Stage();
                stage.setTitle("about database");
                stage.setResizable(false);
                ScrollPane scrollPane = new ScrollPane(vBox);
                Scene scene = new Scene(scrollPane, 600, 300);
                stage.setScene(scene);
                stage.show();
                stage.setAlwaysOnTop(true);
            }
        });

        TreeView tableView = new TreeView();
        TreeItem tablesRootItem = constructRoot(TABLES);
        tablesRootItem.addEventHandler(TreeItem.branchExpandedEvent(), event -> {
            TreeItem<Table> innerTableItem = (TreeItem<Table>) event.getSource();
            if (!innerTableItem.isExpanded()) {
                return;
            }
            Table innerTable = innerTableItem.getValue();
            innerTableItem.getChildren().clear();
            for (Column tableColumn : databaseService.getTableColumns(innerTable.getName())) {
                TreeItem columnItem = new TreeItem(tableColumn);
                innerTableItem.getChildren().add(columnItem);
            }
        });
        tableView.setRoot(tablesRootItem);

        TreeView viewView = new TreeView();
        TreeItem viewsRootItem = constructRoot(VIEWS);
        viewView.setRoot(viewsRootItem);
        VBox leftVBox = new VBox(tableView, viewView);

        tabPane = new TabPane();
        HBox rightHBox = new HBox(tabPane);

        FlowPane contentFlowPane = new FlowPane(Orientation.VERTICAL, leftVBox, rightHBox);
        stageVBox.getChildren().add(contentFlowPane);
        Screen screen = (Screen) Toolkit.getToolkit().getScreens().get(0);
        Scene scene = new Scene(stageVBox, screen.getWidth() / 3 * 2, -1);
        primaryStage.setScene(scene);
        primaryStage.show();

    }


    private TreeItem constructRoot(String rootName) {
        TreeItem tablesRootItem = new TreeItem(rootName);
        tablesRootItem.setExpanded(true);
        List<Table> list;
        if (TABLES.equals(rootName)) {
            list = databaseService.getTables();
        } else {
            if (VIEWS.equals(rootName)) {
                list = databaseService.getViews();
            } else {
                throw new RuntimeException("unknown root name");
            }
        }
        for (Table table : list) {
            TreeItem tableItem = new TreeItem(table);
            tablesRootItem.getChildren().add(tableItem);
            tableItem.getChildren().add(new TreeItem<>(null));
            tableItem.addEventHandler(TreeItem.branchExpandedEvent(), event -> {
                TreeItem treeItem = (TreeItem) event.getSource();
                Table table1 = (Table) treeItem.getValue();
                TableView tableView = constructTable(table1.getName());
                ScrollPane scrollPane = new ScrollPane(tableView);
                Tab tab = new Tab(table1.getName(), scrollPane);
                tabPane.getTabs().add(tab);
            });
        }
        return tablesRootItem;
    }

    private TableView constructTable(String tableName) {
        TableView<Map<String, Object>> tableView = new TableView();
        List<Column> columns = databaseService.getTableColumns(tableName);
        for (Column column : columns) {
            TableColumn<Map<String, Object>, Object> tableColumn = new TableColumn<>(column.getName());
            tableColumn.setCellValueFactory(param -> {
                Map<String, Object> map = param.getValue();
                return new ReadOnlyObjectWrapper<>(map.get(column.getName()));
            });
            tableView.getColumns().add(tableColumn);
        }
        List<Map<String, Object>> list = databaseService.getTableData(tableName, 50);
        for (Map<String, Object> map : list) {
            tableView.getItems().addAll(map);
        }
        return tableView;
    }


}
