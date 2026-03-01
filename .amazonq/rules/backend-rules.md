# Rice Mill Backend - Auto Rules

## Critical Rules
- NEVER modify paddy_stock quantities when threshing - keep original records
- ALWAYS calculate available stock dynamically: additions - (sales + threshing)
- ALWAYS create backup records before update/delete operations
- Use minimal code - avoid verbose implementations
- Rice from threshing: customerName="Threshing", supplierId="N/A", pricePerKg=0.0

## Code Standards
- Use Lombok @Data for entities
- Service interface + implementation pattern
- @Transactional for multi-step operations
- Return descriptive success/error messages
