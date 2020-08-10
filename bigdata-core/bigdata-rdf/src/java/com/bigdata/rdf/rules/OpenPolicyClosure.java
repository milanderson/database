package com.bigdata.rdf.rules;

import com.bigdata.rdf.store.AbstractTripleStore;
import com.bigdata.rdf.rules.custom.*;

public class OpenPolicyClosure extends BaseClosure {
    public OpenPolicyClosure(AbstractTripleStore db) {

        super( db );

    }

    public MappedProgram getProgram(String db, String focusStore) {

        final MappedProgram program = new MappedProgram("fullForwardClosure",
                focusStore, true/* parallel */, true/* closure */);

        program.addStep(new RuleRdfs02(db, vocab));
        program.addStep(new RuleRdfs03(db, vocab));
        program.addStep(new RuleRdfs06(db, vocab));
        program.addStep(new RuleRdfs07(db, vocab));
        program.addStep(new RuleRdfs08(db, vocab));
        program.addStep(new RuleRdfs10(db, vocab));
        program.addStep(new RuleRdfs12(db, vocab));
        program.addStep(new RuleRdfs13(db, vocab));

        program.addStep(new RuleProtonTransOver(db, vocab));
        program.addStep(new RuleProtonTransProp(db, vocab));
        program.addStep(new RuleProtonTransPropInduct(db, vocab));
        program.addStep(new RuleProtonRoleHolder(db, vocab));

        program.addStep(new RuleOwlInverseOf2(db, vocab));
        program.addStep(new RuleOwlSymmetricPropertyByInverse(db, vocab));
        program.addStep(new RuleOwlnverseOfBySymmetricProperty(db, vocab));
        program.addStep(new RuleOwlFunctionalProperty(db, vocab));
        program.addStep(new RuleOwlInverseFunctionalProperty(db, vocab));
        program.addStep(new RuleOwlEquivalentClassBySubclass(db, vocab));
        program.addStep(new RuleOwlEquivalentPropertyBySubProperty(db, vocab));
        program.addStep(new RuleOwlTypeByAllValuesFrom(db, vocab));
        program.addStep(new RuleOwlTypeByHasValue(db, vocab));
        program.addStep(new RuleOwlAttributeByHasValue(db, vocab));
        program.addStep(new RuleOwlTypeBySomeValuesFrom(db, vocab));

        program.addStep((new RuleOwlSubClassByIntersection1_1(db, vocab)));
        program.addStep((new RuleOwlSubClassByIntersection1_2(db, vocab)));
        program.addStep((new RuleOwlSubClassByIntersection2(db, vocab)));
        program.addStep((new RuleOwlTypeByIntersection1(db, vocab)));
        program.addStep((new RuleOwlTypeByIntersection2(db, vocab)));


        program.addStep(new RuleOwlFunctionalPropertybyInverse(db, vocab));
        program.addStep(new RuleOwlInverseFunctionalPropertybyFunctional(db, vocab));
        program.addStep(new RuleOwlAllDifferent1(db, vocab));
        program.addStep(new RuleOwlAllDifferent2(db, vocab));
        program.addStep(new RuleOwlSubClassByUnion1_1(db, vocab));
        program.addStep(new RuleOwlSubClassByUnion1_2(db, vocab));
        program.addStep(new RuleOwlSubClassByUnion2(db, vocab));
        program.addStep(new RuleOwlOneOf1_1(db, vocab));
        program.addStep(new RuleOwlOneOf1_2(db, vocab));
        program.addStep(new RuleOwlOneOf2(db, vocab));
        program.addStep(new RuleOwlSubClassBetweenSomeValue(db, vocab));
        program.addStep(new RuleOwlSubClassBetweenAllValues(db, vocab));
        program.addStep(new RuleOwlSubClassBetweenHasValue(db, vocab));
        program.addStep(new RuleOwlSubClassFromHasValueToSomeValue(db, vocab));

        /* OWL CARDINALITY - Not implemented, but not needed
        program.addStep(new RuleOwlSubClassFromSomeValueToMinCardinality(db, vocab));
        program.addStep(new RuleOwlTypeByMinCardinality(db, vocab));
        program.addStep(new RuleOwlSameAsByMaxCardinality(db, vocab));
        program.addStep(new RuleOwlMinMaxCardinalityByCardinality1(db, vocab));
        program.addStep(new RuleOwlMinMaxCardinalityByCardinality2(db, vocab));
        program.addStep(new RuleOwlMinMaxCardinalityByCardinality3(db, vocab));
        */

        program.addStep(new RuleOwlClassTransitivity(db, vocab));
        program.addStep(new RuleKIMPropertyDomainVisibilityRule(db, vocab));
        program.addStep(new RuleKIMPropertyRangeVisibilityRule(db, vocab));

        program.addStep(new RuleOpenPolicyDocumentGroupContainsDocuments(db, vocab));
        program.addStep(new RuleOpenPolicyParagraphRelatedToSynonyms(db, vocab));


        return program;
    }

}