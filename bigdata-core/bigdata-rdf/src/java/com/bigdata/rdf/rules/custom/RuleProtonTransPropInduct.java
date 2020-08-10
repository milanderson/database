package com.bigdata.rdf.rules.custom;

import com.bigdata.rdf.spo.SPOPredicate;
import com.bigdata.rdf.vocab.Vocabulary;
import com.bigdata.rdf.vocab.custom.ProtonS;
import com.bigdata.relation.rule.Rule;
import org.openrdf.model.vocabulary.OWL;
import org.openrdf.model.vocabulary.RDF;

public class RuleProtonTransPropInduct extends Rule {

    private static final long serialVersionUID = -7423082674586471302L;

    public RuleProtonTransPropInduct(String relationName, Vocabulary vocab) {

        super("proton_TransPropInduct",//
                new SPOPredicate(relationName, var("p"), vocab.getConstant(ProtonS.TRANSITIVEOVER), var("p")), //
                new SPOPredicate[]{ //
                        new SPOPredicate(relationName, var("p"), vocab.getConstant(RDF.TYPE), vocab.getConstant(OWL.TRANSITIVEPROPERTY))
                },//
                null// constraints
        );
    }
}
