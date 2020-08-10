package com.bigdata.rdf.rules.custom;

import com.bigdata.rdf.spo.SPOPredicate;
import com.bigdata.rdf.vocab.Vocabulary;
import com.bigdata.relation.rule.Rule;
import org.openrdf.model.vocabulary.OWL;
import org.openrdf.model.vocabulary.RDF;

public class RuleOwlTypeByMinCardinality extends Rule {

    private static final long serialVersionUID = -7423082674586471336L;

    public RuleOwlTypeByMinCardinality(String relationName, Vocabulary vocab) {

        super("owl_typeByMinCard1",//
                new SPOPredicate(relationName, var("i"), vocab.getConstant(RDF.TYPE), var("r")),
                new SPOPredicate[]{ //
                        new SPOPredicate(relationName, var("r"), vocab.getConstant(OWL.ONPROPERTY), var("p")),
                        //new SPOPredicate(relationName, var("s"), vocab.getConstant(OWL.MINCARDINALITY), ),
                        new SPOPredicate(relationName, var("i"), var("p"), var("j")),
                },//
                null
        );
    }
}