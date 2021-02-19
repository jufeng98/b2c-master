package org.javamaster.b2c.mybatis.model;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created on 2019/2/2.<br/>
 *
 * @author yudong
 */
@Data
@NoArgsConstructor
public class PhoneNumber {
    private String countryCode;
    private String stateCode;
    private String number;

    public PhoneNumber(String string) {
        String[] parts = string.split("-");
        if (parts.length > 0) {
            this.countryCode = parts[0];
        }
        if (parts.length > 1) {
            this.stateCode = parts[1];
        }
        if (parts.length > 2) {
            this.number = parts[2];
        }
    }

    public String getAsString() {
        return countryCode + "-" + stateCode + "-" + number;
    }
}
