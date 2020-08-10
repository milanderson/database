package com.bigdata.rdf.rules.custom;

import com.bigdata.rdf.spo.SPOPredicate;
import com.bigdata.rdf.vocab.Vocabulary;
import com.bigdata.rdf.vocab.custom.ProtonS;
import com.bigdata.relation.rule.Rule;

public class RuleProtonTransOver extends Rule {

    private static final long serialVersionUID = -7423082674586471300L;

    public RuleProtonTransOver(String relationName, Vocabulary vocab) {

        super("proton_TransitiveOver",//
                new SPOPredicate(relationName, var("x"),var("p"), var("z")), //
                new SPOPredicate[]{ //
                        new SPOPredicate(relationName, var("p"), vocab.getConstant(ProtonS.TRANSITIVEOVER), var("q")),
                        new SPOPredicate(relationName, var("x"), var("p"), var("y")),
                        new SPOPredicate(relationName, var("x"), var("q"), var("z"))
                },//
                null// constraints
        );
    }
}
