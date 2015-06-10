package de.cpdfjarrtm.pdfborx.parser.biblio;

import de.cpdfjarrtm.pdfborx.PdfBorx;
import static org.junit.Assert.*;

import java.io.File;
import java.nio.file.FileSystems;
import java.util.ArrayList;

import org.junit.Test;

import de.cpdfjarrtm.pdfborx.data.DataController;
import de.cpdfjarrtm.pdfborx.data.DataControllerImpl;
import de.cpdfjarrtm.pdfborx.data.DocumentModelImpl;
import de.cpdfjarrtm.pdfborx.pdf.document.DocumentPdf;
import de.cpdfjarrtm.pdfborx.pdf.load.LoadController;

/**
 * @author Julian Goetz
 *
 */
public class BiblioParserControllerTest {

    public BiblioParserController bibcontrol;
    public DocumentPdf document;
    public LoadController load;
    public String bibliography;
    public ArrayList<Citation> citations;
    
    public BiblioParserControllerTest(){
        bibcontrol = new BiblioParserController();
        document = null;
        load = null;
        bibliography = null;

        bibliography = "Angeline, P. J. (1997). Tracking extrema in dynamic environments. In Angeline, P. J. et al., editors,\nProc. of the Sixth International Conference on Evolutionary Programming — EP’97, pages 335–\n345. Springer Verlag, Heidelberg.\n16 Evolutionary Computation Volume x, Number x\nOptimum Tracking with Evolution Strategies\nArnold, D. V. (2002). Noisy Optimization with Evolution Strategies. Genetic Algorithms and Evolu-\ntionary Computation Series. Kluwer Academic Publishers, Boston, MA.\nArnold, D. V. and Beyer, H.-G. (2002). Random dynamics optimum tracking with evolution\nstrategies. In Merelo, J. J. et al., editors, Parallel Problem Solving from Nature — PPSN VII,\npages 3–12. Springer Verlag, Heidelberg.\nArnold, D. V. and Beyer, H.-G. (2004). Performance analysis of evolutionary optimization with\ncumulative step length adaptation. IEEE Transactions on Automatic Control, 49(4):617–622.\nBa¨ck, T. (1996). Evolutionary Algorithms in Theory and Practice. OxfordUniversity Press, New York.\nBa¨ck, T. (1998). On the behavior of evolutionary algorithms in dynamic environments. In Proc.\nof the 1998 International Conference on Evolutionary Computation, pages 446–451. IEEE Press,\nPiscataway, NJ.\nBeyer, H.-G. (2001). The Theory of Evolution Strategies. Natural Computing Series. Springer Verlag,\nHeidelberg.\nBeyer, H.-G., Schwefel, H.-P., and Wegener, I. (2002). How to analyze evolutionary algorithms.\nTheoretical Computer Science, 287(1):101–130.\nBranke, J. (2001). Evolutionary Optimization in Dynamic Environments. Genetic Algorithms and\nEvolutionary Computation Series. Kluwer Academic Publishers, Boston, MA.\nBu¨rger, R. (2000). The Mathematical Theory of Selection, Recombination, and Mutation. John Wiley &\nSons, Chichester.\nDroste, S. (2002). Analysis of the (1 + 1) EA for a dynamically changing objective function. In\nProc. of the 2002 Congress on Evolutionary Computation. IEEE Press, Piscataway, NJ.\nGoldberg, D. E. (1989). Genetic Algorithms in Search, Optimization, and Machine Learning. Addison\nWesley, Reading, MA.\nHansen, N. (1998). Verallgemeinerte individuelle Schrittweitenregelung in der Evolutionsstrategie.\nMensch & Buch Verlag, Berlin.\nHansen, N. and Ostermeier, A. (2001). Completely derandomized self-adaptation in evolution\nstrategies. Evolutionary Computation, 9(2):159–195.\nHerdy, M. (1992). Reproductive isolation as a strategy parameter in hierarchically organized\nevolution strategies. In Ma¨nner, R. and Manderick, B., editors, Parallel Problem Solving from\nNature — PPSN II, pages 207–217. Elsevier, Amsterdam.\nJansen, T. and Schellbach, U. (2005). Theoretical analysis of a mutation-based evolutionary algo-\nrithm for a tracking problem in the lattice. In Beyer, H.-G. et al., editors, Proc. of the Genetic\nand Evolutionary Computation Conference — GECCO 2005, pages 841–848. ACM Press, New\nYork.\nJin, Y. and Branke, J. (2005). Evolutionary optimization in uncertain environments — A survey.\nIEEE Transactions on Evolutionary Computation, 9(3):303–317.\nMichalski, R. S. (2000). Learnable evolution model: Evolutionary processes guided by machine\nlearning. Machine Learning, 38(1-2):9–40.\nMitchell, M. (1996). An Introduction to Genetic Algorithms. MIT Press, Cambridge, MA.\nMorrison, R. W. (2004). Designing Evolutionary Algorithms for Dynamic Environments. Natural\nComputing Series. Springer Verlag, Heidelberg.\nOstermeier, A., Gawelczyk, A., and Hansen, N. (1994). Step-size adaptation based on non-local\nuse of selection information. In Davidor, Y., Schwefel, H.-P., and Ma¨nner, R., editors, Parallel\nProblem Solving from Nature — PPSN III, pages 189–198. Springer Verlag, Heidelberg.\nEvolutionary Computation Volume x, Number x 17\nD. V. Arnold and H.-G. Beyer\nRavise´, C. and Sebag, M. (1996). An advanced evolution should not repeat its past errors. In\nSaitta, L., editor, Proc. of the 13th International Conference on Machine Learning, pages 400–408.\nMorgan Kaufmann Publishers, San Francisco, CA.\nRechenberg, I. (1994). Evolutionsstrategie ’94. Frommann-Holzboog Verlag, Stuttgart.\nSalomon, R. and Eggenberger, P. (1997). Adaptation on the evolutionary time scale: A working\nhypothesis and basic experiments. In Hao, J.-K. et al., editors, Proc. of the Third Conference on\nArtificial Evolution — EA’97, pages 251–262. Springer Verlag, Heidelberg.\nSchwefel, H.-P. (1995). Evolution and Optimum Seeking. Wiley, New York.\nSebag, M., Schoenauer, M., and Ravise´, C. (1997). Inductive learning of mutation step-size in\nevolutionary parameter optimization. In Angeline, P. J. et al., editors, Proc. of the Sixth In-\nternational Conference on Evolutionary Programming— EP’97, pages 247–261. Springer Verlag,\nHeidelberg.\nvan Nimwegen, E. and Crutchfield, J. P. (2001). Optimizing epochal evolutionary search:\nPopulation-size dependent theory. Machine Learning, 45(1):77–114.\nWeicker, K. (2006). An empirical investigation of optimum tracking with evolution strategies. In\nProc. of the 9th OnlineWorld Conference on Soft Computing and Industrial Applications —WSC9.\nSpringer Verlag, Heidelberg.\nWeicker, K. and Weicker, N. (1999). On evolution strategy optimization in dynamic environ-\nments. In Proc. of the 1999 Congress on Evolutionary Computation, pages 2039–2046. IEEE\nPress, Piscataway, NJ.\n18 Evolutionary Computation Volume x, Number x\n";
        DataController data = new DataControllerImpl(new DocumentModelImpl());
        load = new LoadController(data);
        document = load.loadDocument(new File("code/test/testdata/arnold2003otes.pdf"));
    }
    
    @Test
    public void testGetCitations() {
        citations = bibcontrol.getCitations(document);

        assertEquals("Angeline, P. J.", citations.get(0).getAuthors());
        assertEquals("Tracking extrema in dynamic environments", citations.get(0).getTitle());
        
        assertEquals("Arnold, D. V. and Beyer, H. G.", citations.get(2).getAuthors());
        assertEquals("Random dynamics optimum tracking with evolution strategies", citations.get(2).getTitle());
        
        assertEquals("Hansen, N. and Ostermeier, A.", citations.get(11).getAuthors());
        assertEquals("Completely derandomized self adaptation in evolution strategies", citations.get(11).getTitle());

    }

    @Test
    public void testGetBibliography() {
        assertEquals(bibliography, bibcontrol.getBibliography(document));
    }

    @Test
    public void testBenchmark() {
        bibliography = bibcontrol.getBibliography(document);
    }

}
