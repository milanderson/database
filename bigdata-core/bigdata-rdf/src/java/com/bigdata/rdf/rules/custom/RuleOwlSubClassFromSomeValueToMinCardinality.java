package com.bigdata.rdf.rules.custom;

import com.bigdata.rdf.spo.SPOPredicate;
import com.bigdata.rdf.vocab.Vocabulary;
import com.bigdata.relation.rule.Rule;
import org.openrdf.model.vocabulary.OWL;
import org.openrdf.model.vocabulary.RDFS;

public class RuleOwlSubClassFromSomeValueToMinCardinality extends Rule {

    private static final long serialVersionUID = -7423082674586471335L;

    public RuleOwlSubClassFromSomeValueToMinCardinality(String relationName, Vocabulary vocab) {

        super("owl_subClassFromSomeValToMinCard1",//
                new SPOPredicate(relationName, var("r"), vocab.getConstant(RDFS.SUBCLASSOF), var("s")),
                new SPOPredicate[]{ //
                        new SPOPredicate(relationName, var("r"), vocab.getConstant(OWL.ONPROPERTY), var("p")),
                        new SPOPredicate(relationName, var("r"), vocab.getConstant(OWL.SOMEVALUESFROM), var("s")),
                        new SPOPredicate(relationName, var("s"), vocab.getConstant(OWL.ONPROPERTY), var("q")),
                        //new SPOPredicate(relationName, var("s"), vocab.getConstant(OWL.MINCARDINALITY), ),
                        new SPOPredicate(relationName, var("p"), vocab.getConstant(RDFS.SUBPROPERTYOF), var("q")),
                },//
                null
        );
    }
}