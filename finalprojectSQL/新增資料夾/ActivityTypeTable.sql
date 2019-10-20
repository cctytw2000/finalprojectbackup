USE [finalproject]
GO

/****** Object:  Table [dbo].[ActivityType]    Script Date: 2019/10/16 ¤U¤È 07:00:14 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[ActivityType](
	[ActivityTypeID] [int] IDENTITY(1,1) NOT NULL,
	[ActivityTypeName] [nvarchar](20) NULL,
 CONSTRAINT [PK_ActivityType] PRIMARY KEY CLUSTERED 
(
	[ActivityTypeID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO


