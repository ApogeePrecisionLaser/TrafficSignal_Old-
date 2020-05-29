package com.ts.junction.tableClasses;

public class SlaveInfo {

    private int junction_id;
    private int program_version_no;
    private int fileNo;
    private String junction_name;
    private int side_no;
    private int side_revision_no;
    private int is_edited;
    private String sideName;
    private String slave_id;
    private String remark;
    private String transferred_status;

    public int getFileNo() {
        return fileNo;
    }

    public void setFileNo(int fileNo) {
        this.fileNo = fileNo;
    }

    public int getIs_edited() {
        return is_edited;
    }

    public void setIs_edited(int is_edited) {
        this.is_edited = is_edited;
    }

    public int getJunction_id() {
        return junction_id;
    }

    public void setJunction_id(int junction_id) {
        this.junction_id = junction_id;
    }

    public String getJunction_name() {
        return junction_name;
    }

    public void setJunction_name(String junction_name) {
        this.junction_name = junction_name;
    }

    public int getProgram_version_no() {
        return program_version_no;
    }

    public void setProgram_version_no(int program_version_no) {
        this.program_version_no = program_version_no;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getSideName() {
        return sideName;
    }

    public void setSideName(String sideName) {
        this.sideName = sideName;
    }

    public int getSide_no() {
        return side_no;
    }

    public void setSide_no(int side_no) {
        this.side_no = side_no;
    }

    public int getSide_revision_no() {
        return side_revision_no;
    }

    public void setSide_revision_no(int side_revision_no) {
        this.side_revision_no = side_revision_no;
    }

    public String getSlave_id() {
        return slave_id;
    }

    public void setSlave_id(String slave_id) {
        this.slave_id = slave_id;
    }

    public String getTransferred_status() {
        return transferred_status;
    }

    public void setTransferred_status(String transferred_status) {
        this.transferred_status = transferred_status;
    }

}
