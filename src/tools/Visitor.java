package tools;

import university.user.Assistant;
import university.user.Teacher;

public interface Visitor {
    void visit(Assistant assistant);
    void visit(Teacher teacher);
}
