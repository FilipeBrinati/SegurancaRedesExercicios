//Nome: Filipe Brinati Furtado
//Matrícula: 201865563C

package exercicio_1;
import java.util.HashMap;
import java.util.Map;

// Definição da classe de permissão
class Permission {
    private String name;

    public Permission(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

// Definição da classe de função
class Role {
    private String name;
    private Map<String, Permission> permissions;

    public Role(String name) {
        this.name = name;
        this.permissions = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public void addPermission(Permission permission) {
        permissions.put(permission.getName(), permission);
    }

    public boolean hasPermission(String permissionName) {
        return permissions.containsKey(permissionName);
    }
}

// Definição da classe de usuário
class User {
    private String name;
    private Role role;

    public User(String name, Role role) {
        this.name = name;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public Role getRole() {
        return role;
    }
}

public class RBACExample {
    public static void main(String[] args) {
        // Criando permissões
        Permission readPermission = new Permission("READ");
        Permission writePermission = new Permission("WRITE");
        Permission modifyPermission = new Permission("MODIFY");
        Permission deletePermission = new Permission("DELETE");

        // Criando funções e atribuindo permissões
        Role adminRole = new Role("ADMIN");
        adminRole.addPermission(readPermission);
        adminRole.addPermission(writePermission);
        adminRole.addPermission(modifyPermission);
        adminRole.addPermission(deletePermission);

        Role teacherRole = new Role("TEACHER");
        teacherRole.addPermission(readPermission);
        teacherRole.addPermission(writePermission);
        teacherRole.addPermission(modifyPermission);

        Role studentRole = new Role("STUDENT");
        studentRole.addPermission(readPermission);

        // Criando usuários e atribuindo funções
        User adminUser = new User("Admin", adminRole);
        User teacherUser = new User("Teacher", teacherRole);
        User studentUser = new User("Student", studentRole);

        // Verificando permissões
        System.out.println(adminUser.getName() + " has READ permission: " + adminUser.getRole().hasPermission("READ"));
        System.out.println(adminUser.getName() + " has WRITE permission: " + adminUser.getRole().hasPermission("WRITE"));
        System.out.println(adminUser.getName() + " has MODIFY permission: " + adminUser.getRole().hasPermission("MODIFY"));
        System.out.println(adminUser.getName() + " has DELETE permission: " + adminUser.getRole().hasPermission("DELETE"));

        System.out.println(teacherUser.getName() + " has READ permission: " + teacherUser.getRole().hasPermission("READ"));
        System.out.println(teacherUser.getName() + " has WRITE permission: " + teacherUser.getRole().hasPermission("WRITE"));
        System.out.println(teacherUser.getName() + " has MODIFY permission: " + teacherUser.getRole().hasPermission("MODIFY"));
        System.out.println(teacherUser.getName() + " has DELETE permission: " + teacherUser.getRole().hasPermission("DELETE"));

        System.out.println(studentUser.getName() + " has READ permission: " + studentUser.getRole().hasPermission("READ"));
        System.out.println(studentUser.getName() + " has WRITE permission: " + studentUser.getRole().hasPermission("WRITE"));
        System.out.println(studentUser.getName() + " has MODIFY permission: " + studentUser.getRole().hasPermission("MODIFY"));
        System.out.println(studentUser.getName() + " has DELETE permission: " + studentUser.getRole().hasPermission("DELETE"));
    }
}
