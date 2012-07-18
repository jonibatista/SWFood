package ipl.estg.dei.meicm.rc;

import java.io.File;

import org.semanticweb.HermiT.Reasoner;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLClassAssertionAxiom;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLIndividual;
import org.semanticweb.owlapi.model.OWLObject;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLObjectPropertyAssertionAxiom;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import org.semanticweb.owlapi.reasoner.OWLReasonerFactory;

public class Ontology {
	private final String OWLPrefix = "http://www.semanticweb.org/ontologies/2012/5/WSFood.owl";
	private OWLOntologyManager manager = null;
	private OWLOntology ontology = null;
	private OWLReasoner reasoner = null;
	private OWLDataFactory fac = null;
	

	public void loadOntology(String owlFile) throws OWLOntologyCreationException {
		manager = OWLManager.createOWLOntologyManager();
		File doc = new File(this.getClass().getClassLoader()
			.getResource(owlFile).getPath());
		ontology = manager.loadOntologyFromOntologyDocument(doc);
	}
	
	public void startInferenceEngine() throws Exception{
		OWLReasonerFactory reasonerFactory = new Reasoner.ReasonerFactory();
		reasoner= reasonerFactory.createReasoner(ontology);
	}
	
	public boolean isOntologyConsistent(){
		reasoner.precomputeInferences();
		if(!reasoner.isConsistent())
			return false;
		
		return true;
	}
	
	public void test(){
		fac = manager.getOWLDataFactory();
		
		// criar individo
		OWLClass vinho;
		String nomeIndivido = "novo_Individuo";
		OWLIndividual individo;
		
		vinho = fac.getOWLClass(IRI.create(OWLPrefix + "#" + "vinho"));
		if(vinho != null && vinho.isOWLClass()){
			individo = fac.getOWLNamedIndividual(IRI.create(OWLPrefix+"#"+nomeIndivido));
			OWLClassAssertionAxiom classAssertion = fac.getOWLClassAssertionAxiom(vinho, individo);
		}
		
		OWLObjectProperty temCor = fac.getOWLObjectProperty(IRI.create(OWLPrefix + "#" + "temCor"));
		//OWLObjectPropertyAssertionAxiom objPropAssertation = fac.getOWLObjectPropertyAssertionAxiom(temCor, individo, true);
		
	}
}
