package dto.response.search;

import dto.criteria.Criteriya;

import java.util.List;

public class CriteriyaResult {
    private Criteriya criteria;
    private List<Customer> results;

    public CriteriyaResult() {
    }

    public CriteriyaResult(Criteriya criteria, List<Customer> results) {
        this.criteria = criteria;
        this.results = results;
    }

    public Criteriya getCriteria() {
        return criteria;
    }

    public void setCriteria(Criteriya criteria) {
        this.criteria = criteria;
    }

    public List<Customer> getResults() {
        return results;
    }

    public void setResults(List<Customer> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "\nCriteriyaResult{" +
                "criteriya=" + criteria +
                ", \nresults=" + results +
                '}';
    }
}
