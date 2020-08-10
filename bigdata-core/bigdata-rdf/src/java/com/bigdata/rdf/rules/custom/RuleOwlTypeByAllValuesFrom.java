package com.bigdata.rdf.rules.custom;

import com.bigdata.rdf.spo.SPOPredicate;
import com.bigdata.rdf.vocab.Vocabulary;
import com.bigdata.relation.rule.Rule;
import org.openrdf.model.vocabulary.OWL;
import org.openrdf.model.vocabulary.RDF;

public class RuleOwlTypeByAllValuesFrom extends Rule {

    private static final long serialVersionUID = -7423082674586471307L;

    public RuleOwlTypeByAllValuesFrom(String relationName, Vocabulary vocab) {

        super("owl_typeByAllVal",//
                new SPOPredicate(relationName, var("v"), vocab.getConstant(RDF.TYPE), var("y")), //
                new SPOPredicate[]{ //
                        new SPOPredicate(relationName, var("x"), vocab.getConstant(OWL.ONPROPERTY), var("p")),
                        new SPOPredicate(relationName, var("u"), vocab.getConstant(RDF.TYPE), var("x")),
                        new SPOPredicate(relationName, var("x"), vocab.getConstant(OWL.ALLVALUESFROM), var("y")),
                        new SPOPredicate(relationName, var("u"), var("p"), var("v")),
                },//
                null// constraints
        );
    }
}