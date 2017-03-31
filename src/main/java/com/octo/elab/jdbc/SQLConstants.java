package com.octo.elab.jdbc;

public class SQLConstants {

	public static final String CFDA_CLAIM_STAGING_QUERY = "SELECT TEMP.\"USERNAME\" , "
			+ "TEMP.\"EMAIL\", TEMP.\"CFDA_USER_ROLE\", TEMP.ORG_ID"
			+ " FROM ( Select Users.\"USERNAME\" , Users.\"EMAIL\", Roles.\"CFDA_USER_ROLE\", Roles.\"CONTACT_ROLE_ID\", "
			+ "case when (Roles.\"USER_LEVEL\") = 'agency' then Roles.\"AGENCY\" when (Roles.\"USER_LEVEL\") = "
			+ "'contracting_office' "
			+ "then (Roles.\"CONTRACTING_OFFICE\") end as ORG_ID from \"CFDA_USERS\".\"AGENCY_CONTACT\" as "
			+ "Users inner join \"CFDA_USERS\".\"AGENCY_CONTACT_ROLE\" as Roles"
			+ " on Users.\"AGENCY_CONTACT_ID\" = Roles.\"AGENCY_CONTACT_ID\" AND "
			+ "Roles.\"CFDA_USER_ROLE\" IS NOT NULL AND Roles.\"CFDA_USER_ROLE\" <> '' AND"
			+ " Users.\"CFDA_ACCOUNT_DISABLED\" = '1' "
			+ "AND Users.\"USERNAME\" is not NULL AND Roles.\"CFDA_USER_ROLE\" <> 'omb_analyst' AND Users.\"EMAIL\" = ? "
			+ "union " + "( SELECT TEMP2.*, " + "OMB.\"AGENCY_ID\" AS ORG_ID " + "FROM ( Select Users.\"USERNAME\" , "
			+ "Users.\"EMAIL\", Roles.\"CFDA_USER_ROLE\", Roles.\"CONTACT_ROLE_ID\" "
			+ "from \"CFDA_USERS\".\"AGENCY_CONTACT\" as Users "
			+ "inner join \"CFDA_USERS\".\"AGENCY_CONTACT_ROLE\" as Roles on Users.\"AGENCY_CONTACT_ID\" = Roles.\"AGENCY_CONTACT_ID\" "
			+ "AND Users.\"CFDA_ACCOUNT_DISABLED\" = '1' AND " + "Users.\"USERNAME\" is not NULL AND "
			+ "Roles.\"CFDA_USER_ROLE\" = 'omb_analyst' " + "AND Users.\"EMAIL\" = ? ) "
			+ "as TEMP2 INNER JOIN \"CFDA_USERS\".\"ACCOUNT_OMB_AGENCY\" "
			+ "AS OMB ON OMB.\"CONTACT_ROLE_ID\" = TEMP2.\"CONTACT_ROLE_ID\" ) ) "
			+ "AS TEMP left join \"CFDA_USERS\".\"FBOOBJECTPICKS\" AS Perm ON TEMP.\"CONTACT_ROLE_ID\" = Perm.\"OBJECT_ID\" ";

	public static final String FH_ORG_LOOKUP_CFDA_QUERY = "SELECT ORG_KEY from FHSCHEMA.ORG WHERE ORG.SOURCE_CFDA_PK = ?";
	public static final String FH_ORG_LOOKUP_FPDS_QUERY = "SELECT ORG_KEY from FHSCHEMA.ORG WHERE ORG.FPDS_CODE = ?";

	public static final String CFDA_CLAIM_LOCAL_QUERY = "SELECT * from CFDA_USERS.AGENCY_CONTACT "
			+ "WHERE AGENCY_CONTACT.EMAIL=? OR AGENCY_CONTACT.USERNAME=?";

	public static final String CFDA_CLAIM_STAGING_LEGACY_QUERY = " SELECT " + " TEMP.USERNAME , " + " TEMP.EMAIL, "
			+ " TEMP.CFDA_USER_ROLE, " + " TEMP.ORG_ID " + " FROM  " + " (	Select  " + " Users.USERNAME , "
			+ " Users.EMAIL, " + " Roles.CFDA_USER_ROLE, " + " Roles.CONTACT_ROLE_ID, " + " case  "
			+ " when (Roles.USER_LEVEL) = 'agency' then Roles.AGENCY  "
			+ " when (Roles.USER_LEVEL) = 'contracting_office' then  " + " (Roles.CONTRACTING_OFFICE)  "
			+ " end as ORG_ID " + " from CFDA_AGENCY_CONTACT as Users "
			+ " inner join CFDA_AGENCY_CONTACT_ROLE as Roles "
			+ " on Users.AGENCY_CONTACT_ID = Roles.AGENCY_CONTACT_ID "
			+ " AND Roles.CFDA_USER_ROLE IS NOT NULL AND  Roles.CFDA_USER_ROLE <> ''  "
			+ " AND Users.CFDA_ACCOUNT_DISABLED = '0'  " + " AND Users.USERNAME is not NULL  "
			+ " AND Roles.CFDA_USER_ROLE <> 'omb_analyst'  AND Users.EMAIL = ? " + " union " + " ( " + " SELECT  "
			+ " TEMP2.*, " + " OMB.agency_id AS ORG_ID " + " FROM " + " ( " + " Select  " + " Users.USERNAME , "
			+ " Users.EMAIL, " + " Roles.CFDA_USER_ROLE, " + " Roles.CONTACT_ROLE_ID  "
			+ " from CFDA_AGENCY_CONTACT as Users " + " inner join CFDA_AGENCY_CONTACT_ROLE as Roles "
			+ " on Users.AGENCY_CONTACT_ID = Roles.AGENCY_CONTACT_ID " + " AND Users.CFDA_ACCOUNT_DISABLED = '0'  "
			+ " AND Users.USERNAME is not NULL  " + " AND Roles.CFDA_USER_ROLE = 'omb_analyst' AND Users.EMAIL = ? "
			+ " ) as TEMP2 " + "inner join cfda_account_omb_agency as OMB "
			+ " on TEMP2.CONTACT_ROLE_ID = OMB.contact_role_id " + " ) " + " ) AS TEMP ";
	public static final String FPDS_CLAIM_STAGING_LEGACY_QUERY = "select distinct "
			+ "RoleInfo.fpds_user_id as USERNAME, " + "RoleInfo.role_name as CFDA_USER_ROLE, "
			+ "UserInfo.email_address as EMAIL, " + "UserInfo.agency_code as ORG_ID "
			+ "from public.fpds_user_privilege as RoleInfo " + "inner join "
			+ "public.fpds_user_profile as UserInfo on " + "RoleInfo.fpds_user_id = UserInfo.fpds_user_id "
			+ "where RoleInfo.fpds_user_id = ? or RoleInfo.fpds_user_id = ? ";

	public static final String FPDS_CLAIM_FETCH_ROLEFUNCPERM_QUERY = "select distinct "
			+ "RoleInfo.fpds_user_id as USERNAME, " + "RoleInfo.role_name as ROLE, "
			+ "RoleInfo.object_name as FUNCTION, " + "RoleInfo.function_name as PERMISSION "
			+ "from public.fpds_user_privilege as RoleInfo " + "inner join "
			+ "public.fpds_user_profile as UserInfo on " + "RoleInfo.fpds_user_id = UserInfo.fpds_user_id "
			+ "where RoleInfo.fpds_user_id = ? and RoleInfo.role_name = ? ";
}
