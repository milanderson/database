/*

Copyright (C) SYSTAP, LLC DBA Blazegraph 2006-2016.  All rights reserved.

Contact:
     SYSTAP, LLC DBA Blazegraph
     2501 Calvert ST NW #106
     Washington, DC 20008
     licenses@blazegraph.com

This program is free software; you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation; version 2 of the License.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program; if not, write to the Free Software
Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA

*/
package com.bigdata.rdf.axioms;

import com.bigdata.rdf.model.BigdataStatement;
import com.bigdata.rdf.model.BigdataValueFactory;
import com.bigdata.rdf.model.StatementEnum;
import com.bigdata.rdf.vocab.custom.*;
import org.openrdf.model.vocabulary.OWL;
import org.openrdf.model.vocabulary.RDF;
import org.openrdf.model.vocabulary.RDFS;
import org.openrdf.model.vocabulary.XMLSchema;

import java.util.Collection;

/**
 * The axioms for RDF Schema plus a few axioms to support owl:sameAs,
 * owl:equivalentProperty, and owl:equivalentClass.
 *
 * @author personickm
 * @author <a href="mailto:mikeanders42@gmail.com">Michael Anderson</a>
 * @version $Id$
 */
public class OpenPolicyAxioms extends BaseAxioms {
    /**
     *
     */
    private static final long serialVersionUID = -5941985779347312839L;

    /**
     * De-serialization ctor.
     */
    public OpenPolicyAxioms() {

        super();

    }

    public OpenPolicyAxioms(final String namespace) {

        super(namespace);

    }

    protected void addAxioms(final Collection<BigdataStatement> axioms) {

        super.addAxioms(axioms);

        final BigdataValueFactory valueFactory = getValueFactory();

        // RDF axiomatic triples (from RDF Semantics, section 3.1):
        axioms.add( valueFactory.createStatement(RDF.TYPE, RDF.TYPE, RDF.PROPERTY, null, StatementEnum.Axiom));
        axioms.add( valueFactory.createStatement(RDF.SUBJECT, RDF.TYPE, RDF.PROPERTY, null, StatementEnum.Axiom));
        axioms.add( valueFactory.createStatement(RDF.PREDICATE, RDF.TYPE, RDF.PROPERTY, null, StatementEnum.Axiom));
        axioms.add( valueFactory.createStatement(RDF.OBJECT, RDF.TYPE, RDF.PROPERTY, null, StatementEnum.Axiom));
        axioms.add( valueFactory.createStatement(RDF.FIRST, RDF.TYPE, RDF.PROPERTY, null, StatementEnum.Axiom));
        axioms.add( valueFactory.createStatement(RDF.REST, RDF.TYPE, RDF.PROPERTY, null, StatementEnum.Axiom));
        axioms.add( valueFactory.createStatement(RDF.VALUE, RDF.TYPE, RDF.PROPERTY, null, StatementEnum.Axiom));
        axioms.add( valueFactory.createStatement(RDF.NIL, RDF.TYPE, RDF.PROPERTY, null, StatementEnum.Axiom));

        // OWL trivial statements in addition (OWL Horst)
        // the OWL schema should be imported as part of the OWLMemSchemaRepository initialization:
        axioms.add( valueFactory.createStatement(OWL.EQUIVALENTCLASS, RDF.TYPE, OWL.TRANSITIVEPROPERTY, null, StatementEnum.Axiom));
        axioms.add( valueFactory.createStatement(OWL.EQUIVALENTCLASS, RDF.TYPE, OWL.SYMMETRICPROPERTY, null, StatementEnum.Axiom));
        axioms.add( valueFactory.createStatement(OWL.EQUIVALENTCLASS, RDFS.SUBPROPERTYOF, RDFS.SUBCLASSOF, null, StatementEnum.Axiom));
        axioms.add( valueFactory.createStatement(OWL.EQUIVALENTPROPERTY, RDF.TYPE, OWL.TRANSITIVEPROPERTY, null, StatementEnum.Axiom));
        axioms.add( valueFactory.createStatement(OWL.EQUIVALENTPROPERTY, RDF.TYPE, OWL.SYMMETRICPROPERTY, null, StatementEnum.Axiom));
        axioms.add( valueFactory.createStatement(OWL.EQUIVALENTCLASS, RDFS.SUBPROPERTYOF, RDFS.SUBPROPERTYOF, null, StatementEnum.Axiom));


        axioms.add( valueFactory.createStatement(OWL.INVERSEOF, RDF.TYPE, OWL.SYMMETRICPROPERTY, null, StatementEnum.Axiom));

        // those properties are implemented using owl:TransitiveProperty for performance reasons
        // The specific RDFS rule are removed from the final ruleset [rdfs5, rdfs11]
        axioms.add( valueFactory.createStatement(RDFS.SUBCLASSOF, RDF.TYPE, OWL.TRANSITIVEPROPERTY, null, StatementEnum.Axiom));
        axioms.add( valueFactory.createStatement(RDFS.SUBPROPERTYOF, RDF.TYPE, OWL.TRANSITIVEPROPERTY, null, StatementEnum.Axiom));

        // The [rdfs9] rule is removed from the final ruleset. Impelemnted as follows
        axioms.add( valueFactory.createStatement(RDF.TYPE, ProtonS.TRANSITIVEOVER, RDFS.SUBCLASSOF, null, StatementEnum.Axiom));

        // owl:differentFrom is symmetric
        axioms.add( valueFactory.createStatement(OWL.DIFFERENTFROM, RDF.TYPE, OWL.SYMMETRICPROPERTY, null, StatementEnum.Axiom));
        axioms.add( valueFactory.createStatement(XMLSchema.NON_NEGATIVE_INTEGER, RDF.TYPE, RDFS.DATATYPE, null, StatementEnum.Axiom));
        axioms.add( valueFactory.createStatement(XMLSchema.STRING, RDF.TYPE, RDFS.DATATYPE, null, StatementEnum.Axiom));

    }

    @Override
    public boolean isNone() {
        return false;
    }

    @Override
    public boolean isRdfSchema() {
        return true;
    }

    @Override
    public boolean isOwlSameAs() {
        return true;
    }
}
