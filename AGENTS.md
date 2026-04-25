# AGENTS.md - LuciaStore

## Architecture Overview
LuciaStore is a console-based Java application using a layered architecture:
- **Domain**: Entities like `Customer`, `Product`, `Category` with enums (`CustomerTypeEnum`, `ProductState`).
- **Repository**: In-memory data storage (e.g., `CustomerRepository` uses `ArrayList<Customer>`).
- **Service**: Business logic with interfaces (e.g., `CustumerService`) and implementations (e.g., `CustumerServiceImpl`).
- **View**: Console interaction (e.g., `CustomerView` calls service methods).
- **UserInterface**: Menu handling (`MenuApp` orchestrates views).
- **Config**: Dependency injection in `Config.createMenuApp()`.

Data flows from UI → View → Service → Repository → Domain.

## Key Patterns
- **Service Layer**: Interfaces in `services/` with `Impl` classes. Inject repositories via constructors.
- **Validation**: Static methods in `utils/` (e.g., `CustomerFormValidation.validateInt()` for input).
- **Enum Selection**: Dedicated selectors (e.g., `CustomerStateSelector.selectCustomerState()`) return enum descriptions.
- **Console UI**: `Scanner` instances in services/views for input; no web framework.

## Conventions
- Package: `storeapp.{domain,repository,services,userinterface,view,utils}`.
- Naming: Services like `CustumerService` (note: "Custumer" is intentional, not typo).
- Enums: With `description` field (e.g., `CustomerTypeEnum.NEW_CUSTOMER.getDescription()`).
- No persistence: All data in-memory; repositories initialize with sample data.
- Build: Maven (`pom.xml`); Java 21; no external dependencies.

## Workflows
- **Compile**: `mvn compile` (outputs to `target/classes`).
- **Run**: `java -cp target/classes storeapp.Main`.
- **Debug**: Add `System.out.println()` in services/repositories; no logging framework.
- **Add Feature**: Create domain entity, repository, service interface/impl, view, wire in `Config`, add menu in `MenuApp`.

## Examples
- Create customer: `CustomerView.createCustomer()` → `CustumerServiceImpl.createCustomer()` → `CustomerRepository.saveCustomer()`.
- Validate input: `int id = CustomerFormValidation.validateInt("Enter ID");`.
- Select enum: `customer.setCustomerType(CustomerTypeSelector.selectTypeCustomer());`.</content>
<parameter name="filePath">C:\DESARROLLO\SEMESTRE_II\Backend\LuciaStore\AGENTS.md
