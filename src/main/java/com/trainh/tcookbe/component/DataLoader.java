
package com.trainh.tcookbe.component;

import com.trainh.tcookbe.model.entity.*;
import com.trainh.tcookbe.model.enums.ERole;
import com.trainh.tcookbe.model.enums.EStatus;
import com.trainh.tcookbe.repository.*;
import jakarta.annotation.PostConstruct;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class DataLoader {
    private final RoleRepository roleRepository;
    private final StatusRepository statusRepository;
    private final UserRepository userRepository;
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final PrimaryCategoryRepository primaryCategoryRepository;
    private final CategoryRepository categoryRepository;
    private final TagRepository tagRepository;

    public DataLoader(RoleRepository roleRepository, StatusRepository statusRepository, UserRepository userRepository, AccountRepository accountRepository, PasswordEncoder passwordEncoder, PrimaryCategoryRepository primaryCategoryRepository, CategoryRepository categoryRepository, TagRepository tagRepository) {
        this.roleRepository = roleRepository;
        this.statusRepository = statusRepository;
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
        this.primaryCategoryRepository = primaryCategoryRepository;
        this.categoryRepository = categoryRepository;
        this.tagRepository = tagRepository;
    }

//    @PostConstruct
    public void loadData() {
        loadRoleData();
        loadStatusData();
        loadUserData();
        loadPrimaryCategory();
        loadTag();
        loadCategory();
    }

    private void loadRoleData() {
        if (roleRepository.count() == 0) {
            List<Role> roles = Arrays.asList(
                    new Role(ERole.ROLE_USER),
                    new Role(ERole.ROLE_MODERATOR),
                    new Role(ERole.ROLE_ADMIN)
            );
            roleRepository.saveAll(roles);
        }
    }

    private void loadStatusData() {
        if (statusRepository.count() == 0) {
            List<Status> statuses = Arrays.asList(
                    new Status(EStatus.ACTIVE),
                    new Status(EStatus.INACTIVE),
                    new Status(EStatus.DELETED),
                    new Status(EStatus.HIDE),
                    new Status(EStatus.SHOW),
                    new Status(EStatus.PENDING),
                    new Status(EStatus.APPROVED)
            );
            statusRepository.saveAll(statuses);
        }
    }

    private void loadUserData() {
        if (userRepository.count() == 0) {
            loadUser();
        }
    }

    private void loadUser() {
        Set<Role> rolesUser = new HashSet<>();
        Set<Role> rolesMod = new HashSet<>();
        Set<Role> rolesAdmin = new HashSet<>();

        Optional<Role> roleUserOptional = roleRepository.findByName(ERole.ROLE_USER);
        Optional<Role> roleModeratorOptional = roleRepository.findByName(ERole.ROLE_MODERATOR);
        Optional<Role> roleAdminOptional = roleRepository.findByName(ERole.ROLE_ADMIN);
        Optional<Status> statusOptional = statusRepository.findByName(EStatus.ACTIVE);
        if (roleUserOptional.isPresent() && roleModeratorOptional.isPresent() && roleAdminOptional.isPresent() && statusOptional.isPresent()) {

            rolesUser.add(roleUserOptional.get());
            User user = new User("user", "user");
            user.setRoles(rolesUser);
            user.setStatus(statusOptional.get());
            Account accountUser = new Account("user", passwordEncoder.encode("123456"));
            accountUser.setUser(user);
            accountRepository.save(accountUser);

            rolesMod.add(roleAdminOptional.get());
            User admin = new User("admin", "admin");
            admin.setStatus(statusOptional.get());
            Account accountAdmin = new Account("admin", passwordEncoder.encode("123456"));
            admin.setRoles(rolesAdmin);
            accountAdmin.setUser(admin);
            accountRepository.save(accountAdmin);

            rolesMod.add(roleModeratorOptional.get());
            User mod = new User("mod", "mod");
            mod.setStatus(statusOptional.get());
            Account accountMod = new Account("mod", passwordEncoder.encode("123456"));
            mod.setRoles(rolesMod);
            accountMod.setUser(mod);
            accountRepository.save(accountMod);



        }
    }

    private void loadPrimaryCategory() {
        if (primaryCategoryRepository.count() == 0) {
            Optional<Status> statusOptional = statusRepository.findByName(EStatus.SHOW);
            if (statusOptional.isPresent()) {
                List<PrimaryCategory> primaryCategories = Arrays.asList(
                        new PrimaryCategory("Nguyên liệu", statusOptional.get()),
                        new PrimaryCategory("Phương pháp nấu", statusOptional.get()),
                        new PrimaryCategory("Xuất xứ", statusOptional.get()),
                        new PrimaryCategory("Lễ tết", statusOptional.get()),
                        new PrimaryCategory("Chè", statusOptional.get()),
                        new PrimaryCategory("Bánh 3 miền", statusOptional.get())
                );
                primaryCategoryRepository.saveAll(primaryCategories);
            }
        }
    }

    private void loadCategory() {
        if (categoryRepository.count() == 0) {
            Optional<Status> statusOptional = statusRepository.findByName(EStatus.SHOW);
            Optional<PrimaryCategory> primaryCategoryOptional = primaryCategoryRepository.findByName("Bánh 3 miền");
            if (statusOptional.isPresent() && primaryCategoryOptional.isPresent()) {
                List<Category> categories = Arrays.asList(
                        new Category("Bánh miền Nam", statusOptional.get(), primaryCategoryOptional.get()),
                        new Category("Bánh miền Bắc", statusOptional.get(), primaryCategoryOptional.get()),
                        new Category("Bánh miền Trung", statusOptional.get(), primaryCategoryOptional.get())
                );
                categoryRepository.saveAll(categories);
            }
        }
    }

    private void loadTag() {
        if (tagRepository.count() == 0) {
            List<Tag> tags = Arrays.asList(
                    new Tag("bánh"),
                    new Tag("chè")
            );
            tagRepository.saveAll(tags);
        }
    }
}