package com.bigdata.rdf.rules.custom;

import com.bigdata.rdf.spo.SPOPredicate;
import com.bigdata.rdf.vocab.Vocabulary;
import com.bigdata.rdf.vocab.custom.ProtonS;
import com.bigdata.relation.rule.Rule;
import org.openrdf.model.vocabulary.RDF;

public class RuleProtonTransProp extends Rule {

    private static final long serialVersionUID = -7423082674586471301L;

    public RuleProtonTransProp(String relationName, Vocabulary vocab) {

        super("proton_TransProp",//
                new SPOPredicate(relationName, var("p"), vocab.getConstant(ProtonS.TRANSITIVEOVER), var("p")), //
                new SPOPredicate[]{ //
                        new SPOPredicate(relationName, var("p"), vocab.getConstant(RDF.TYPE), vocab.getConstant(ProtonS.TRANSITIVEOVER))
                },//
                null// constraints
        );
    }
}

