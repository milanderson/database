package com.bigdata.rdf.rules.custom;

import com.bigdata.rdf.spo.SPOPredicate;
import com.bigdata.rdf.vocab.Vocabulary;
import com.bigdata.relation.rule.Rule;
import org.openrdf.model.vocabulary.OWL;

public class RuleOwlMinMaxCardinalityByCardinality2 extends Rule {

    private static final long serialVersionUID = -7423082674586471339L;

    public RuleOwlMinMaxCardinalityByCardinality2(String relationName, Vocabulary vocab) {

        super("owl_minMaxCardByCard2",//
                new SPOPredicate(relationName, var("w"), vocab.getConstant(OWL.MINCARDINALITY), var("w")),
                new SPOPredicate[]{ //
                        new SPOPredicate(relationName, var("r"), vocab.getConstant(OWL.ONPROPERTY), var("p")),
                        //new SPOPredicate(relationName, var("r"), vocab.getConstant(OWL.MINCARDINALITY), ),
                },//
                null
        );
    }
}