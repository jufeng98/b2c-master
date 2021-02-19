package com.javamaster.b2c.cloud.test.learn.java.model;

import lombok.Builder;

/**
 * @author yudong
 * @date 2019/6/14
 */
@Builder
public class Style {

    private String fontName;
    private int fontColor;
    private int fontBold;
    private int fontUnderline;
    private boolean fontItalic;
    private int fontHeightInPoints;

    private int foregroundColor;
    private int backgroundColor;

    private boolean needLeftBorder;
    private boolean needRightBorder;
    private boolean needTopBorder;
    private boolean needBottomBorder;
    private int borderColor;
    private int borderWeight;

    private boolean autoLineFeed;
    private int alignment;
    private int verticalAlignment;

    private String datePattern;
    private String decimalPattern;
}
