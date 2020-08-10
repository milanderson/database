package com.bigdata.rdf.rules.custom;

import com.bigdata.rdf.spo.SPOPredicate;
import com.bigdata.rdf.vocab.Vocabulary;
import com.bigdata.rdf.vocab.custom.KimSO;
import com.bigdata.relation.rule.Rule;
import org.openrdf.model.vocabulary.OWL;
import org.openrdf.model.vocabulary.RDF;
import org.openrdf.model.vocabulary.RDFS;

public class RuleKIMPropertyRangeVisibilityRule extends Rule {

    private static final long serialVersionUID = -7423082674586471332L;

    public RuleKIMPropertyRangeVisibilityRule(String relationName, Vocabulary vocab) {

        super("PropertyRangeVisibilityRule",//
                new SPOPredicate(relationName, var("p"), vocab.getConstant(KimSO.VISIBILITYLEVEL1), var("q")),
                new SPOPredicate[]{ //
                        new SPOPredicate(relationName, var("r"), vocab.getConstant(RDFS.RANGE), var("p")),
                        new SPOPredicate(relationName, var("r"), vocab.getConstant(KimSO.VISIBILITYLEVEL1), var("q")),
                        new SPOPredicate(relationName, var("p"), vocab.getConstant(RDF.TYPE), vocab.getConstant(OWL.CLASS)),
                },//
                null
        );
    }
}