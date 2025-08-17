# Flyway Database Migration Guide

## Overview
This project uses Flyway for database schema version control and automatic migration management.

## How It Works

### 1. Migration Files Location
- **Path**: `src/main/resources/migration/versions/`
- **Naming Convention**: `V{version}__{description}.sql`
- **Example**: `V1__Create_Admin_Table.sql`

### 2. Current Migrations
- **V1**: Creates the `admins` table with proper indexes
- **V2**: Creates the `access_token` table for storing active access tokens
- **Status**: Will run automatically on first application startup
- **History**: Recorded in `flyway_schema_history` table

### 3. Migration Execution
- **First Run**: Creates tables and records success in history
- **Subsequent Runs**: Skips execution (already applied)
- **Validation**: Ensures database schema matches migration files
- **Order**: Migrations run in version order (V1, then V2, etc.)

### 4. Configuration
```yaml
spring:
  flyway:
    enabled: true
    locations: classpath:migration/versions
    baseline-on-migrate: true
    validate-on-migrate: true
```

### 5. Benefits
- ✅ **One-time Execution**: Each migration runs only once
- ✅ **Version Control**: Database schema is version-controlled
- ✅ **Team Collaboration**: All developers get same database structure
- ✅ **Production Safety**: Migrations are tracked and validated

### 6. Adding New Migrations
1. Create new file: `V2__{description}.sql`
2. Place in `migration/versions/` folder
3. Restart application
4. Flyway automatically detects and runs new migrations

### 7. Migration Details

#### V1__Create_Admin_Table.sql
- **Purpose**: Creates the `admins` table for user authentication
- **Features**: User management with roles, timestamps, and indexes
- **Indexes**: username, email, role, created_at

#### V2__Create_Access_Token_Table.sql
- **Purpose**: Creates the `access_token` table for token management
- **Features**: Stores active access and refresh tokens
- **Fields**: user_id, tokens (VARCHAR(500)), expiration, active status, system generation flag
- **Relations**: Foreign key to `admins` table
- **Indexes**: user_id, username, email, tokens (100 chars), active status, expiration

### 8. Migration History
Check the `flyway_schema_history` table to see:
- Which migrations have been applied
- When they were executed
- Their success/failure status
- Checksum validation

## Important Notes
- **Never modify existing migration files** after they've been applied
- **Always test migrations** in development before production
- **Backup database** before running migrations in production
- **Migration order** is determined by version numbers (V1, V2, V3, etc.)
- **Dependencies**: V2 depends on V1 (foreign key relationship)
