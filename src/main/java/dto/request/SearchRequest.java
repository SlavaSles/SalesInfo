package dto.request;

import dto.criteria.Criteria;

import java.util.ArrayList;
import java.util.List;

public class SearchRequest extends Request {

    private List<Criteria> criterias = new ArrayList<>();

    public SearchRequest() {
    }

    public List<Criteria> getCriterias() {
        return criterias;
    }

    public void setCriterias(List<Criteria> criterias) {
        this.criterias = criterias;
    }

    @Override
    public String toString() {
        return "SearchRequest{" +
                "criterias=" + criterias +
                '}';
    }
}
