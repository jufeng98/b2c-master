package com.javamaster.b2c.cloud.test.pattern.effectivejava.clone;

public class PhoneNumber implements Cloneable {
    private final short areaCode;
    private final short prefix;
    private final short lineNumber;

    public static void main(String[] args) {
        PhoneNumber[] phoneNumbers = new PhoneNumber[]{new PhoneNumber((short) 23, (short) 23, (short) 234)};
        System.out.println(phoneNumbers[0]);
        System.out.println(phoneNumbers.clone()[0]);
    }

    public PhoneNumber(short areaCode, short prefix, short lineNumber) {
        check(!(areaCode < 0 || areaCode > 999), "area code");
        check(!(prefix < 0 || prefix > 999), "prefix");
        check(!(lineNumber < 0 || lineNumber > 9999), "line number");
        this.areaCode = areaCode;
        this.prefix = prefix;
        this.lineNumber = lineNumber;
    }

    private void check(boolean b, String msg) {
        if (!b) {
            throw new IllegalArgumentException(msg);
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + areaCode;
        result = prime * result + lineNumber;
        result = prime * result + prefix;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        PhoneNumber other = (PhoneNumber) obj;
        if (areaCode != other.areaCode)
            return false;
        if (lineNumber != other.lineNumber)
            return false;
        if (prefix != other.prefix)
            return false;
        return true;
    }

    @Override
    public PhoneNumber clone() {
        try {
            return (PhoneNumber) super.clone();
        } catch (Exception e) {
            throw new AssertionError();
        }
    }

    @Override
    public String toString() {
        return "PhoneNumber [areaCode=" + areaCode + ", prefix=" + prefix + ", lineNumber=" + lineNumber + "]";
    }

    public short getAreaCode() {
        return areaCode;
    }

    public short getPrefix() {
        return prefix;
    }

    public short getLineNumber() {
        return lineNumber;
    }

}
