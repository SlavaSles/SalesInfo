package dto.response.search;

import dto.response.Response;
import dto.response.ResponseType;

import java.util.ArrayList;
import java.util.List;

public class SearchResponse extends Response {
//    Может нужно будет заменить Enum на String
    private ResponseType type = ResponseType.SEARCH;
    private List<CriteriyaResult> results;

    public SearchResponse() {
    }

    public SearchResponse(ResponseType type) {
        this.type = type;
        this.results = new ArrayList<>();
    }

    public ResponseType getType() {
        return type;
    }

    public void setType(ResponseType type) {
        this.type = type;
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
