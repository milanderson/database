package com.bigdata.rdf.rules.custom;

import com.bigdata.rdf.spo.SPOPredicate;
import com.bigdata.rdf.vocab.Vocabulary;
import com.bigdata.relation.rule.Rule;
import org.openrdf.model.vocabulary.OWL;

public class RuleOwlMinMaxCardinalityByCardinality1 extends Rule {

    private static final long serialVersionUID = -7423082674586471338L;

    public RuleOwlMinMaxCardinalityByCardinality1(String relationName, Vocabulary vocab) {

        super("owl_minMaxCardByCard1",//
                new SPOPredicate(relationName, var("w"), vocab.getConstant(OWL.ONPROPERTY), var("p")),
                new SPOPredicate[]{ //
                        new SPOPredicate(relationName, var("r"), vocab.getConstant(OWL.ONPROPERTY), var("p")),
                        //new SPOPredicate(relationName, var("r"), vocab.getConstant(OWL.MINCARDINALITY), ),
                },//
                null
        );
    }
}
