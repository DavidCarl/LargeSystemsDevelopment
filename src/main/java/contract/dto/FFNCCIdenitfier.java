package contract.dto;

public class FFNCCIdenitfier {
    private String ffcc;

    public FFNCCIdenitfier(String ffcc) {
        this.ffcc = ffcc;
    }

    public FFNCCIdenitfier() {
    }

    public String getFfcc() {
        return ffcc;
    }

    public void setFfcc(String ffcc) {
        this.ffcc = ffcc;
    }
}
