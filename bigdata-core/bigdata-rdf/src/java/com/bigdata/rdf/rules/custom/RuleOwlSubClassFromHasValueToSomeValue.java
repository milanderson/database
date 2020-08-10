package com.bigdata.rdf.rules.custom;

import com.bigdata.rdf.spo.SPOPredicate;
import com.bigdata.rdf.vocab.Vocabulary;
import com.bigdata.relation.rule.Rule;
import org.openrdf.model.vocabulary.OWL;
import org.openrdf.model.vocabulary.RDF;
import org.openrdf.model.vocabulary.RDFS;

public class RuleOwlSubClassFromHasValueToSomeValue extends Rule {

    private static final long serialVersionUID = -7423082674586471329L;

    public RuleOwlSubClassFromHasValueToSomeValue(String relationName, Vocabulary vocab) {

        super("owl_subClassFromHasValToSomeVal",//
                new SPOPredicate(relationName, var("r"), vocab.getConstant(RDFS.SUBCLASSOF), var("s")), //
                new SPOPredicate[]{ //
                        new SPOPredicate(relationName, var("r"), vocab.getConstant(OWL.ONPROPERTY), var("p")),
                        new SPOPredicate(relationName, var("r"), vocab.getConstant(OWL.HASVALUE), var("i")),
                        new SPOPredicate(relationName, var("i"), vocab.getConstant(RDF.TYPE), var("c")),
                        new SPOPredicate(relationName, var("s"), vocab.getConstant(OWL.ONPROPERTY), var("q")),
                        new SPOPredicate(relationName, var("s"), vocab.getConstant(OWL.SOMEVALUESFROM), var("c")),
                        new SPOPredicate(relationName, var("p"), vocab.getConstant(RDFS.SUBPROPERTYOF), var("q")),
                },//
                null
        );
    }
}
