package pl.put.poznan.transformer.model;

import java.util.List;

/**
 * Klasa do odebrania jsona z requestu
 * Ma text który chcemy transformować i listę transformacji do zastosowania
 */
public class TransformRequest {
    private String text;
    private List<String> transforms;

    public TransformRequest() {
    }

    public TransformRequest(String text, List<String> transforms) {
        this.text = text;
        this.transforms = transforms;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<String> getTransforms() {
        return transforms;
    }

    public void setTransforms(List<String> transforms) {
        this.transforms = transforms;
    }

    @Override
    public String toString() {
        return "TransformRequest{" +
                "text='" + text + '\'' +
                ", transforms=" + transforms +
                '}';
    }
}
