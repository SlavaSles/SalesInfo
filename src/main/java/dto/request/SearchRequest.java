package dto.request;

import dto.criteria.Criteriya;

import java.util.ArrayList;
import java.util.List;

public class SearchRequest extends Request {

    private List<Criteriya> criteriyas = new ArrayList<>();

    public SearchRequest() {
    }

    public List<Criteriya> getCriteriyas() {
        return criteriyas;
    }

    public void setCriteriyas(List<Criteriya> criteriyas) {
        this.criteriyas = criteriyas;
    }

    @Override
    public String toString() {
        return "SearchRequest{" +
                "criterias=" + criteriyas +
                '}';
    }
}
