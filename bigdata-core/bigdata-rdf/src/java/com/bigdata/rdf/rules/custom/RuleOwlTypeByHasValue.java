package com.bigdata.rdf.rules.custom;

import com.bigdata.rdf.spo.SPOPredicate;
import com.bigdata.rdf.vocab.Vocabulary;
import com.bigdata.relation.rule.Rule;
import org.openrdf.model.vocabulary.OWL;
import org.openrdf.model.vocabulary.RDF;

public class RuleOwlTypeByHasValue extends Rule {

    private static final long serialVersionUID = -7423082674586471308L;

    public RuleOwlTypeByHasValue(String relationName, Vocabulary vocab) {

        super("owl_typeByHasVal",//
                new SPOPredicate(relationName, var("i"), vocab.getConstant(RDF.TYPE), var("r")), //
                new SPOPredicate[]{ //
                        new SPOPredicate(relationName, var("r"), vocab.getConstant(OWL.ONPROPERTY), var("p")),
                        new SPOPredicate(relationName, var("r"), vocab.getConstant(OWL.HASVALUE), var("v")),
                        new SPOPredicate(relationName, var("i"), var("p"), var("v")),
                },//
                null// constraints
        );
    }
}