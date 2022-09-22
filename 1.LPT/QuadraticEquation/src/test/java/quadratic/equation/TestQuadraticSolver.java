package quadratic.equation;

import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNull;

public class TestQuadraticSolver {

    @Test
    public void aloneRoot() {
        QuadraticSolver solver = new QuadraticSolver(1, 2, 1);
        solver.solve();
        double aloneRoot = solver.getZ1().getReal();
        assertEquals(-1f, aloneRoot, QuadraticSolver.EPSILON);
    }

    @Test
    public void realRoots() {
        QuadraticSolver solver = new QuadraticSolver(0.5, 0.125, 0);
        solver.solve();
        double x1 = solver.getZ1().getReal();
        double x2 = solver.getZ2().getReal();
        assertEquals(0, x1, QuadraticSolver.EPSILON);
        assertEquals(-0.25, x2, QuadraticSolver.EPSILON);
    }
    @Test
    public void linearEquation() {
        QuadraticSolver solver = new QuadraticSolver(0, 2, 1);
        solver.solve();
        double x1 = solver.getZ1().getReal();
        assertEquals(-0.5, x1, QuadraticSolver.EPSILON);
        assertNull(solver.getZ2());
    }

    @Test
    public void ComplexRoots() {
        QuadraticSolver solver = new QuadraticSolver(1, 2, 5);
        solver.solve();
        double x1 = solver.getZ1().getReal();
        double x2 = solver.getZ2().getReal();

        double y1 = solver.getZ1().getImaginary();
        double y2 = solver.getZ2().getImaginary();
        assertEquals(-1, x1, QuadraticSolver.EPSILON);
        assertEquals(-1, x2, QuadraticSolver.EPSILON);
        assertEquals(2, y1, QuadraticSolver.EPSILON);
        assertEquals(-2, y2, QuadraticSolver.EPSILON);
    }

}
