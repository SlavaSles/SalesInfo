package dto.response.search;

import dto.response.Response;
import dto.response.ResponseType;

import java.util.ArrayList;
import java.util.List;

public class SearchResponse extends Response {
    private ResponseType type;
    private List<CriteriyaResult> results;

    public SearchResponse() {
        this.type = ResponseType.search;
        this.results = new ArrayList<>();
    }

    public ResponseType getType() {
        return type;
    }

    public List<CriteriyaResult> getResults() {
        return results;
    }

    public void setResults(List<CriteriyaResult> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "SearchResponse{" +
                "type=" + type +
                ", criteriyaResults=" + results +
                '}';
    }
}
