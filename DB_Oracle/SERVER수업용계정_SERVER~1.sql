SELECT 
        USER_NO
      , USER_ID
      , USER_PWD
      , USER_NAME
      , PHONE
      , EMAIL
      , ADDRESS
      , INTEREST
      , ENROLL_DATE
      , MODIFY_DATE
      , STATUS
FROM MEMBER
WHERE USER_ID = 'user01'
AND USER_PWD = 'pass01'
AND STATUS = 'Y';