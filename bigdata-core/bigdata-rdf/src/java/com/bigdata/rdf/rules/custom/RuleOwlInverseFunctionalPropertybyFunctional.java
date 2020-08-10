package com.bigdata.rdf.rules.custom;

import com.bigdata.rdf.spo.SPOPredicate;
import com.bigdata.rdf.vocab.Vocabulary;
import com.bigdata.relation.rule.Rule;
import org.openrdf.model.vocabulary.OWL;
import org.openrdf.model.vocabulary.RDF;

public class RuleOwlInverseFunctionalPropertybyFunctional extends Rule {

    private static final long serialVersionUID = -7423082674586471312L;

    public RuleOwlInverseFunctionalPropertybyFunctional(String relationName, Vocabulary vocab) {

        super("owl_InvFunctPropByFunctProp",//
                new SPOPredicate(relationName, var("q"), vocab.getConstant(RDF.TYPE), vocab.getConstant(OWL.INVERSEFUNCTIONALPROPERTY)), //
                new SPOPredicate[]{ //
                        new SPOPredicate(relationName, var("p"), vocab.getConstant(RDF.TYPE), vocab.getConstant(OWL.FUNCTIONALPROPERTY)),
                        new SPOPredicate(relationName, var("p"), vocab.getConstant(OWL.INVERSEOF), var("q")),
                },//
                null// constraints
        );
    }
}