package it.pn.frontend.e2e.model.enums;

public enum TokenLogin {
    PF_DELEGATE_TOKEN(
            "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6IjBBS3RoQjlYNS1fNHRWUGxNRmZUTHdWSkt1akxuSEhkODdSUkdtX1VTR0EifQ.eyJuYW1lIjoiR2FpbyBHaXVsaW8iLCJmYW1pbHlfbmFtZSI6IkNlc2FyZSIsInVpZCI6ImEwMWI2MmQ0LWUzZjgtNDhmMy1hNGQ4LWNmNjI4YTM0Yjc0NSIsImZpc2NhbF9udW1iZXIiOiJDU1JHR0w0NEwxM0g1MDFFIiwic3BpZF9sZXZlbCI6Imh0dHBzOi8vd3d3LnNwaWQuZ292Lml0L1NwaWRMMiIsImZyb21fYWEiOmZhbHNlLCJhdWQiOiJjaXR0YWRpbmkuZGV2Lm5vdGlmaWNoZWRpZ2l0YWxpLml0IiwibGV2ZWwiOiJMMiIsImlhdCI6MTY5OTg4NjU3OCwiZXhwIjoyMDE1NDQ5MjAwLCJpc3MiOiJodHRwczovL2h1Yi1sb2dpbi5zcGlkLmRldi5ub3RpZmljaGVkaWdpdGFsaS5pdCIsImp0aSI6Il9iMGI5MGYzNjc2ZTA1MjYxMzAzYyJ9.PaTdY6Hl8RmIJdmobl0ahjawsWdmXrehnWEzRgmQT4Wf7i4Lvz8h_FgXUtt9TB8rZ1t92j2XrqOip1Pa_fSXAUv0Gx4wgb1W4ll74iPs7Mf1sXsZxFQyrd_XwTcvnRYHalAsOizbINNckm-NB0Vo-bRzlgpqSN0dnZ2r9lYSryf5H-B7214mlXvmQu-QD64iyLibdmU8cFim9E5A3hIT9vCGdSGZBsLGkC_oYhmMGLz-N4UeChJid98c37mI4EGpFMwJ7sifDgggCzHbaQBdBKlNPzRIosGZLju6zILzZsEpVkIhYNi5suf5sJeHR56adXKlYIkMWUc8Gdh5g5YskA",
            "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6IkU4aDZkaUZ2amtCdk1kYVF3SjBMWFlyVW5BeDYwWm1NQTFoUVc3UGtrSVkifQ.eyJzcGlkX2xldmVsIjoiaHR0cHM6Ly93d3cuc3BpZC5nb3YuaXQvU3BpZEwyIiwibmFtZSI6IkdhaW8gR2l1bGlvIiwiZmFtaWx5X25hbWUiOiJDZXNhcmUiLCJ1aWQiOiJhMDFiNjJkNC1lM2Y4LTQ4ZjMtYTRkOC1jZjYyOGEzNGI3NDUiLCJmaXNjYWxfbnVtYmVyIjoiQ1NSR0dMNDRMMTNINTAxRSIsImZyb21fYWEiOmZhbHNlLCJhdWQiOiJjaXR0YWRpbmkudGVzdC5ub3RpZmljaGVkaWdpdGFsaS5pdCIsImxldmVsIjoiTDIiLCJpYXQiOjE2OTk4OTI1OTIsImV4cCI6MjAxNTQ0OTIwMCwiaXNzIjoiaHR0cHM6Ly9odWItbG9naW4uc3BpZC50ZXN0Lm5vdGlmaWNoZWRpZ2l0YWxpLml0IiwianRpIjoiXzdkMjdlYTcwMWEzZDljMzgzZGI1In0.t4JdDC9BrO__yVJ0cX7fDm_RfUSgZZdGhDnE4Uq77JjIVmi2oQz9dejom6scBa49A0VqETGeBkm32t145YIbJruNg5umyUIHJwr7tNk6YCHnk2_moBcLY2qFBBl66L95-yG0R3nTJAt1whhBkoxMqFlA7_5EgHtSAGhXK040owuUINm1aVPQXRUnvyTI8pqPVKj4cQM-sESVtdr5Bn81h8WUlnm3htIq-VlYWi-ntyYVpo1vCDQPJpytPqzS6qh6mVweTKdorLmWSpPpTARB8klaRPzkYaVA54Bb2Z64hmpPWmKcX8heXbO_lTzuRCzoe-GFAD-k2FkmIGWMjdxYFA"),
    PF_DELEGATOR_TOKEN(
            "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6IjBBS3RoQjlYNS1fNHRWUGxNRmZUTHdWSkt1akxuSEhkODdSUkdtX1VTR0EifQ.eyJuYW1lIjoiTHVjcmV6aWEiLCJmYW1pbHlfbmFtZSI6IkJvcmdpYSIsInNwaWRfbGV2ZWwiOiJodHRwczovL3d3dy5zcGlkLmdvdi5pdC9TcGlkTDIiLCJ1aWQiOiIyY2JkMzkzZi0yNmI2LTQxODYtOTg4Ny1jYzAzZWE1MzFlYzciLCJmaXNjYWxfbnVtYmVyIjoiQlJHTFJaODBENThINTAxUSIsImZyb21fYWEiOmZhbHNlLCJhdWQiOiJjaXR0YWRpbmkuZGV2Lm5vdGlmaWNoZWRpZ2l0YWxpLml0IiwibGV2ZWwiOiJMMiIsImlhdCI6MTY5OTg4NzkxOSwiZXhwIjoyMDE1NDQ5MjAwLCJpc3MiOiJodHRwczovL2h1Yi1sb2dpbi5zcGlkLmRldi5ub3RpZmljaGVkaWdpdGFsaS5pdCIsImp0aSI6Il9hZDY2NzE3Y2QxNDE1M2JmZjliMSJ9.KKA-NAKvnrRW5aj79H3dmhZgc3k-5n4k2rih172Y5Y8SDXFJbJzgscvfB8hzstuLpoXybr4cGtVwuk7ML82u2r0KMz_rQ5TLC3kTi3hakK0vF-lHZQKarh2PayYWzXAqOEkKws2KtWn9aAB1vFrkccOKmxi0XOZ0v15Mg1p4IKVOb4DihIcbMSGZXAGNJUCyL7nKiH8cy7nR81hGf6loDMEjvWHXI9GAm1rNLcSyKhcq1cHc9Y5vpBpTsgwlYxsYeXUbZHbDa_g5-_K3JXaxaAN75lcfcvZFl80Zq297YoE2C0tZSRnBI8owO6ewJgZFKFK7qlP_jjcD98Ttstf9HQ",
            "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6IkU4aDZkaUZ2amtCdk1kYVF3SjBMWFlyVW5BeDYwWm1NQTFoUVc3UGtrSVkifQ.eyJuYW1lIjoiTHVjcmV6aWEiLCJmYW1pbHlfbmFtZSI6IkJvcmdpYSIsInNwaWRfbGV2ZWwiOiJodHRwczovL3d3dy5zcGlkLmdvdi5pdC9TcGlkTDIiLCJ1aWQiOiJkZjJhMTI2OC1lY2ZlLTQzMzAtYjY1MC01MTg0YTdlYjA3NTYiLCJmaXNjYWxfbnVtYmVyIjoiQlJHTFJaODBENThINTAxUSIsImZyb21fYWEiOmZhbHNlLCJhdWQiOiJjaXR0YWRpbmkudGVzdC5ub3RpZmljaGVkaWdpdGFsaS5pdCIsImxldmVsIjoiTDIiLCJpYXQiOjE2OTk4ODk3ODUsImV4cCI6MjAxNTQ0OTIwMCwiaXNzIjoiaHR0cHM6Ly9odWItbG9naW4uc3BpZC50ZXN0Lm5vdGlmaWNoZWRpZ2l0YWxpLml0IiwianRpIjoiXzFmNTZjYjQ3MjM0M2EzMDExYTgyIn0.kq9aHDxtTZ7tzvLiIIDH88ZnIHXl5OR0j4iQldPQuwomDo3BqCPEev1Tg1HtTNKEE1p5rQw4mIJKYdSO2aARZanpYcmh49HmFtK8HBLnJtlVD59pIlHWcijpODGJkZuH8Jp8_nupFVEr_G-wsWeixsfjjPR09gA5gVFi13WTeZroz1_cIaZiuF8odwYXa5-vJoJCdVF-jEsSv4uHy_XtZVI8yffc3GQH5E0mHFA8Ua-Owdb15LPfHFsC5lWDemNa6C9f_qABWTCxS24geTWFgulB1gi5aFL8intFsnvLlODTeemPDG2gljS6OJYzFdqh9CwtTMLdjMdg3y5UzoPy0A"),
    PG_DELEGATE_TOKEN(
            "eyJraWQiOiJqd3QtZXhjaGFuZ2VfYzc6ZTI6NjQ6YzI6YzY6MTg6Nzk6Nzc6NDc6MDY6NzM6Nzk6OGQ6ZGY6MTA6ZTYiLCJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJmYW1pbHlfbmFtZSI6IkFsaWdoaWVyaSIsImZpc2NhbF9udW1iZXIiOiJMR0hETlQwMFAxNEQ2MTJEIiwibmFtZSI6IkRhbnRlIiwic3BpZF9sZXZlbCI6Imh0dHBzOi8vd3d3LnNwaWQuZ292Lml0L1NwaWRMMiIsImZyb21fYWEiOmZhbHNlLCJ1aWQiOiJlNDkwZjAyZS05NDI5LTRiMzgtYmIxMS1kZGI4YTU2MWZiNjIiLCJsZXZlbCI6IkwyIiwiaWF0IjoxNzAwMjIyOTQ5LCJleHAiOjE5MDAyMjM4NDksImF1ZCI6ImltcHJlc2UuZGV2Lm5vdGlmaWNoZWRpZ2l0YWxpLml0IiwiaXNzIjoiaHR0cHM6Ly9wbnBnLnVhdC5zZWxmY2FyZS5wYWdvcGEuaXQiLCJqdGkiOiIwMGNhMDA3Zi0xNjQxLTRkNDUtYjM1MC0xZDY4MzBmYTQ3MzQiLCJlbWFpbCI6IkRhbnRlQWxpZ2hpZXJpQHBhcmFkaXNvLml0Iiwib3JnYW5pemF0aW9uIjp7ImlkIjoiZDBmNTJjN2QtNzZkNS00NTIwLTg5NzEtZWRmZmViNWI0NmQ1IiwibmFtZSI6IkNvbnZpdmlvIFNwYSIsInJvbGVzIjpbeyJwYXJ0eVJvbGUiOiJNQU5BR0VSIiwicm9sZSI6InBnLWFkbWluIn1dLCJzdWJVbml0Q29kZSI6bnVsbCwic3ViVW5pdFR5cGUiOm51bGwsImFvb1BhcmVudCI6bnVsbCwicGFyZW50RGVzY3JpcHRpb24iOm51bGwsInJvb3RQYXJlbnQiOnsiaWQiOm51bGwsImRlc2NyaXB0aW9uIjpudWxsfSwiZmlzY2FsX2NvZGUiOiIyNzk1NzgxNDQ3MCIsImlwYUNvZGUiOiIyNzk1NzgxNDQ3MCJ9LCJkZXNpcmVkX2V4cCI6MTkwMDIyMzg0OX0.FRNqnCz9WjeUSZ7URfAdmyjusxcePlDM7wmK038KJSSbLTkIbuq_Yhz4DIQN6wvK0WeQjNc2NZ5dKOQV9nuMzujXSvM3zoFA1VeGuxwDFCcBNPlDKCj281G8nuSlLLuaLD-dqZ9ObaEwAd5d1xTzhC0-D3pR34kQ1Ms0vK6xHrhCC7DHGLMY4IGyDYfnqS-b9-oeg61mCjk5nBaK1gltnr0Y_bfI0GhVYOrR06cTCeyh-tfpexsMzGtUYt24sLnIOHu9DU0BQv8AywHsu1zb_jtugC81TgZEKhZmSNsYcqFT1j-Eg30zHISjrMe6-xqgXdmSyhwrFrDyJHwnGnssGA",
            "eyJraWQiOiJqd3QtZXhjaGFuZ2VfYzc6ZTI6NjQ6YzI6YzY6MTg6Nzk6Nzc6NDc6MDY6NzM6Nzk6OGQ6ZGY6MTA6ZTYiLCJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJmYW1pbHlfbmFtZSI6IkFsaWdoaWVyaSIsImZpc2NhbF9udW1iZXIiOiJMR0hETlQwMFAxNEQ2MTJEIiwibmFtZSI6IkRhbnRlIiwic3BpZF9sZXZlbCI6Imh0dHBzOi8vd3d3LnNwaWQuZ292Lml0L1NwaWRMMiIsImZyb21fYWEiOmZhbHNlLCJ1aWQiOiJlNDkwZjAyZS05NDI5LTRiMzgtYmIxMS1kZGI4YTU2MWZiNjIiLCJsZXZlbCI6IkwyIiwiaWF0IjoxNzAwMjIzMDE3LCJleHAiOjE5MDAyMjM5MTcsImF1ZCI6ImltcHJlc2UudGVzdC5ub3RpZmljaGVkaWdpdGFsaS5pdCIsImlzcyI6Imh0dHBzOi8vcG5wZy51YXQuc2VsZmNhcmUucGFnb3BhLml0IiwianRpIjoiMmVlYmY5ZTMtYTBkYS00NTEzLTkxMWQtNjMyMjBjODE2NTIyIiwiZW1haWwiOiJEYW50ZUFsaWdoaWVyaUBwYXJhZGlzby5pdCIsIm9yZ2FuaXphdGlvbiI6eyJpZCI6ImQwZjUyYzdkLTc2ZDUtNDUyMC04OTcxLWVkZmZlYjViNDZkNSIsIm5hbWUiOiJDb252aXZpbyBTcGEiLCJyb2xlcyI6W3sicGFydHlSb2xlIjoiTUFOQUdFUiIsInJvbGUiOiJwZy1hZG1pbiJ9XSwic3ViVW5pdENvZGUiOm51bGwsInN1YlVuaXRUeXBlIjpudWxsLCJhb29QYXJlbnQiOm51bGwsInBhcmVudERlc2NyaXB0aW9uIjpudWxsLCJyb290UGFyZW50Ijp7ImlkIjpudWxsLCJkZXNjcmlwdGlvbiI6bnVsbH0sImZpc2NhbF9jb2RlIjoiMjc5NTc4MTQ0NzAiLCJpcGFDb2RlIjoiMjc5NTc4MTQ0NzAifSwiZGVzaXJlZF9leHAiOjE5MDAyMjM5MTd9.YCN_gcThDAj7Xf4H6aWUGGuO7f5NwdfRin9bD3uUy9IcX1w3WsiuscUQNvJdCo4PCyL0MFrQsB2-Xyf7O-ygguRpzDeK8rxMvgAvxJaQEiKv0FllF2b90cTsubIHdaY0VdgOhmLubuZcPGVpOfP14uC77J7cgYmrYTLIL28PE-JloFY3NTwOdxpgsEU3cSoNB13GabzWTwzAji-2d3dfW6e2_-ybd0LZhzukj3ev0OprG0ZP-4aIRXK8Arr1R5V_zYZR6kRxdpvRTLRsr12Mcc7AiJF6LWkc8Ddem8RfHzBhneXhWRghn6iwCR1x-kRETWxUYBSyd72gqn8DaLQFoQ"),
    PG_DELEGATOR_TOKEN(
            "eyJraWQiOiJqd3QtZXhjaGFuZ2VfYzc6ZTI6NjQ6YzI6YzY6MTg6Nzk6Nzc6NDc6MDY6NzM6Nzk6OGQ6ZGY6MTA6ZTYiLCJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJmYW1pbHlfbmFtZSI6IlBldHJhcmNhIiwiZmlzY2FsX251bWJlciI6IlBUUkZOQzA0QTAxQzM1MkUiLCJuYW1lIjoiRnJhbmNlc2NvIiwic3BpZF9sZXZlbCI6Imh0dHBzOi8vd3d3LnNwaWQuZ292Lml0L1NwaWRMMiIsImZyb21fYWEiOmZhbHNlLCJ1aWQiOiJkNWE4YTM4MC05MTBkLTQ5ZGMtOWZiOC04NDcwMGM2NDQxMGYiLCJsZXZlbCI6IkwyIiwiaWF0IjoxNzAwMjIzMDkzLCJleHAiOjE5MDAyMjM5OTMsImF1ZCI6ImltcHJlc2UuZGV2Lm5vdGlmaWNoZWRpZ2l0YWxpLml0IiwiaXNzIjoiaHR0cHM6Ly9wbnBnLnVhdC5zZWxmY2FyZS5wYWdvcGEuaXQiLCJqdGkiOiJjODA0Y2M1Ny0xZWU5LTQzZTYtOWFkMy00NTVlMmVlNTAwOWIiLCJlbWFpbCI6IkZyYW5jZXNjb1BldHJhcmNhQHNlY3JldHVtLml0Iiwib3JnYW5pemF0aW9uIjp7ImlkIjoiZTI0ODYxMjYtNDM5NC00MzAwLWEyNWQtYjZkODc5ZmExNzE3IiwibmFtZSI6IkxlIEVwaXN0b2xhZSBzcmwiLCJyb2xlcyI6W3sicGFydHlSb2xlIjoiTUFOQUdFUiIsInJvbGUiOiJwZy1hZG1pbiJ9XSwic3ViVW5pdENvZGUiOm51bGwsInN1YlVuaXRUeXBlIjpudWxsLCJhb29QYXJlbnQiOm51bGwsInBhcmVudERlc2NyaXB0aW9uIjpudWxsLCJyb290UGFyZW50Ijp7ImlkIjpudWxsLCJkZXNjcmlwdGlvbiI6bnVsbH0sImZpc2NhbF9jb2RlIjoiTEVMUFRSMDRBMDFDMzUyRSIsImlwYUNvZGUiOiJMRUxQVFIwNEEwMUMzNTJFIn0sImRlc2lyZWRfZXhwIjoxOTAwMjIzOTkzfQ.IrTkY_p7atg-v3aZo-9JkcBEqa8DR_5wb54g5Ylbewb5PrjGIEmLsmhY7L-D3-NSD4HgqmeolRtlplUAWfnMMVa9jfHurNr-NpeoOV5GV7kBd-uyIwnS_DK9KZq7AZeoAHP-Pz5p0ZMSuuJ2-uzx1zkJopvX-6uOZWwFl_OZQELCDWNJ2hgYPCKZvxxtSDyknHdKm_laIBQmZdlxAMr2y_Q9b2eqbdcRQGrGHa_Zkoo2qbIRZWW2EGcbfk6rgIoMdU_7JCGGHARkL0Y7OY9f9BIDfjLxudAbh73xiZJh-hMMkx73CXI2oyjmM40rytR9AyESkV8pFWSFNtiQ_NzR9A",
            "eyJraWQiOiJqd3QtZXhjaGFuZ2VfYzc6ZTI6NjQ6YzI6YzY6MTg6Nzk6Nzc6NDc6MDY6NzM6Nzk6OGQ6ZGY6MTA6ZTYiLCJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJmYW1pbHlfbmFtZSI6IlBldHJhcmNhIiwiZmlzY2FsX251bWJlciI6IlBUUkZOQzA0QTAxQzM1MkUiLCJuYW1lIjoiRnJhbmNlc2NvIiwic3BpZF9sZXZlbCI6Imh0dHBzOi8vd3d3LnNwaWQuZ292Lml0L1NwaWRMMiIsImZyb21fYWEiOmZhbHNlLCJ1aWQiOiJkNWE4YTM4MC05MTBkLTQ5ZGMtOWZiOC04NDcwMGM2NDQxMGYiLCJsZXZlbCI6IkwyIiwiaWF0IjoxNzAwMjIzMTY1LCJleHAiOjE5MDAyMjQwNjUsImF1ZCI6ImltcHJlc2UudGVzdC5ub3RpZmljaGVkaWdpdGFsaS5pdCIsImlzcyI6Imh0dHBzOi8vcG5wZy51YXQuc2VsZmNhcmUucGFnb3BhLml0IiwianRpIjoiNGM5NDEwMzAtZWY2Yi00NDUwLTlhMWEtNDI2MmVhNWIyMThiIiwiZW1haWwiOiJGcmFuY2VzY29QZXRyYXJjYUBzZWNyZXR1bS5pdCIsIm9yZ2FuaXphdGlvbiI6eyJpZCI6ImUyNDg2MTI2LTQzOTQtNDMwMC1hMjVkLWI2ZDg3OWZhMTcxNyIsIm5hbWUiOiJMZSBFcGlzdG9sYWUgc3JsIiwicm9sZXMiOlt7InBhcnR5Um9sZSI6Ik1BTkFHRVIiLCJyb2xlIjoicGctYWRtaW4ifV0sInN1YlVuaXRDb2RlIjpudWxsLCJzdWJVbml0VHlwZSI6bnVsbCwiYW9vUGFyZW50IjpudWxsLCJwYXJlbnREZXNjcmlwdGlvbiI6bnVsbCwicm9vdFBhcmVudCI6eyJpZCI6bnVsbCwiZGVzY3JpcHRpb24iOm51bGx9LCJmaXNjYWxfY29kZSI6IkxFTFBUUjA0QTAxQzM1MkUiLCJpcGFDb2RlIjoiTEVMUFRSMDRBMDFDMzUyRSJ9LCJkZXNpcmVkX2V4cCI6MTkwMDIyNDA2NX0.K2kOmvd4KlGsq3aVuEsa0qywTqd7Mt0Q7CqtDjlAVeVY-j-aEnipjMTxZHHk5gUCxztKcidt_C81tqKEWWRvOUaMi5TUk7zsdy_zvjC6LEs_Ib3PXMpEU2MNyj5QjobaYTUw-Qi08_2aPPxJyH1AOi7sfGreKM3VHVjg4Mt24eabg7vD2A_Voaxg_HGNjhzl8N5z33goRnybLaYAj6gnT_KY-Z_dM7fXoVKJHsU0PPaqKwIlPEuPtCpeRYdYJShjAq10FKTcLjeYT2amub_FOjOnoEVQ8aDCLt0Y_xc0MG0kc4gx4zEFjUT1rrGlfm9pZ8sx9d9yr190L4Hsh8cSyg"),
    PA_TOKEN(
            "eyJraWQiOiJqd3QtZXhjaGFuZ2VfZWE6NDg6NTI6ZTQ6YWU6OGY6MzA6YjU6YWQ6M2M6ZDI6MDU6NzQ6Nzk6Yzk6ZWYiLCJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJmYW1pbHlfbmFtZSI6IkJhcm9uZSIsImZpc2NhbF9udW1iZXIiOiJCUk5CQ0g5MUw0OUg4MjJFIiwibmFtZSI6IkJhY2NoaXNpbyIsInNwaWRfbGV2ZWwiOiJodHRwczovL3d3dy5zcGlkLmdvdi5pdC9TcGlkTDIiLCJmcm9tX2FhIjpmYWxzZSwidWlkIjoiMjEzNGQ5MmItNWQxYi00OWI1LTlhNmUtNDI4M2Y0ODc5YmJmIiwibGV2ZWwiOiJMMiIsImlhdCI6MTcwMDIyMjY2MiwiZXhwIjoxOTAwMjIyNjc3LCJhdWQiOiJzZWxmY2FyZS5kZXYubm90aWZpY2hlZGlnaXRhbGkuaXQiLCJpc3MiOiJodHRwczovL3VhdC5zZWxmY2FyZS5wYWdvcGEuaXQiLCJqdGkiOiJhYWY3NWE0NS03MmYwLTRmY2ItOWI5MC01YjliNWMyMTQ1YWMiLCJlbWFpbCI6InJzYW5uYUBnbWFpbC5jb20iLCJvcmdhbml6YXRpb24iOnsiaWQiOiJhOTVkYWNlNC00YTQ3LTQxNDktYTgxNC0wZTY2OTExM2NlNDAiLCJuYW1lIjoiQ29tdW5lIGRpIFZlcm9uYSIsInJvbGVzIjpbeyJwYXJ0eVJvbGUiOiJNQU5BR0VSIiwicm9sZSI6ImFkbWluIn1dLCJncm91cHMiOlsiNjMyMWQzMjU2NGZmMDYxYTUxNDY0YjlkIl0sInN1YlVuaXRDb2RlIjpudWxsLCJzdWJVbml0VHlwZSI6bnVsbCwiYW9vUGFyZW50IjpudWxsLCJwYXJlbnREZXNjcmlwdGlvbiI6bnVsbCwicm9vdFBhcmVudCI6eyJpZCI6bnVsbCwiZGVzY3JpcHRpb24iOm51bGx9LCJmaXNjYWxfY29kZSI6IjAwMjE1MTUwMjM2IiwiaXBhQ29kZSI6ImNfbDc4MSJ9LCJkZXNpcmVkX2V4cCI6MTkwMDIyMjY3N30.NKZRi2mFvcWeWbrOiqZEqji3V9DQ3dUg3vk9yrZxaNEnKVU4l3nHuip_Ej9qZiyJvN5lPmvU4PhuLCRM1rIZcTIhyXO0uAJFbjBudt1j0oa7ErW-G394ab4dErUWarikhHs_xSW1hgAbOEHfwKPXTsb1-N5c0nl3wEG84YZ0fBdOtFKR__DHVrYyx5PkixaiN2Ch4pTAYnI8myAjsfO3MTEEvVb2qO-9qvpjG-4swG-hzk36NyG1J1jenUHvNvqhymb3maNoZ-aiVZocVZumCRxmWxrGSWmqFXwsD-QwE4iltfCNKqvF4ONKc-itNoAPXjLkFvJqILYiv07XajFaXA",
            "eyJraWQiOiJqd3QtZXhjaGFuZ2VfZWE6NDg6NTI6ZTQ6YWU6OGY6MzA6YjU6YWQ6M2M6ZDI6MDU6NzQ6Nzk6Yzk6ZWYiLCJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJmYW1pbHlfbmFtZSI6IkJhcm9uZSIsImZpc2NhbF9udW1iZXIiOiJCUk5CQ0g5MUw0OUg4MjJFIiwibmFtZSI6IkJhY2NoaXNpbyIsInNwaWRfbGV2ZWwiOiJodHRwczovL3d3dy5zcGlkLmdvdi5pdC9TcGlkTDIiLCJmcm9tX2FhIjpmYWxzZSwidWlkIjoiMjEzNGQ5MmItNWQxYi00OWI1LTlhNmUtNDI4M2Y0ODc5YmJmIiwibGV2ZWwiOiJMMiIsImlhdCI6MTcwMDIyMjU0NiwiZXhwIjoxOTAwMjIyNTYxLCJhdWQiOiJzZWxmY2FyZS50ZXN0Lm5vdGlmaWNoZWRpZ2l0YWxpLml0IiwiaXNzIjoiaHR0cHM6Ly91YXQuc2VsZmNhcmUucGFnb3BhLml0IiwianRpIjoiZTA2NGI1MmYtMjUyNC00MDEyLWI5N2QtZjZlNmYyZDYzMmMyIiwiZW1haWwiOiJyc2FubmFAZ21haWwuY29tIiwib3JnYW5pemF0aW9uIjp7ImlkIjoiYTk1ZGFjZTQtNGE0Ny00MTQ5LWE4MTQtMGU2NjkxMTNjZTQwIiwibmFtZSI6IkNvbXVuZSBkaSBWZXJvbmEiLCJyb2xlcyI6W3sicGFydHlSb2xlIjoiTUFOQUdFUiIsInJvbGUiOiJhZG1pbiJ9XSwic3ViVW5pdENvZGUiOm51bGwsInN1YlVuaXRUeXBlIjpudWxsLCJhb29QYXJlbnQiOm51bGwsInBhcmVudERlc2NyaXB0aW9uIjpudWxsLCJyb290UGFyZW50Ijp7ImlkIjpudWxsLCJkZXNjcmlwdGlvbiI6bnVsbH0sImZpc2NhbF9jb2RlIjoiMDAyMTUxNTAyMzYiLCJpcGFDb2RlIjoiY19sNzgxIn0sImRlc2lyZWRfZXhwIjoxOTAwMjIyNTYxfQ.r4QCr_qE5JZMlWiBmpKDqA9D6iCog0xcodO7g-NzTklZh5lxlrw_FVlqxQi7WA5BdYsPIlvqytGLBCQxQfiTKvuO-ieK_L1W4QxK5R_3DqNWwB1UuNssIWV1nhta2chKZhmZpZisI6MhVTmqBx18Eyh1iEoO1nbM6lfokRNOz2a4gvYqCKkmC58hbWCYFcpIqvxDyEed5fLr84NvcaQxopydCIBIMEcthyHWrWuO7AplFMd-6FOtMm3k4PMSEj6586T0CfZ-IVAqXZw-tP_A1ubygfEhrQGaVNPufkk5eAL_oFtgKb2VaRoyf4tyv0zCogNC9vwnMQZaBuzXiZIHsg");

    private final String devToken;
    private final String testToken;

    private TokenLogin(String devToken, String testToken) {
        this.devToken = devToken;
        this.testToken = testToken;
    }

    public String getToken() {
        String env = System.getProperty("environment");
        return env.equalsIgnoreCase("dev") ? devToken : testToken;
    }
}
