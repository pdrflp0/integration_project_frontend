[Setup]
AppId={{MainClass}}
AppName=ProjectIntegration
AppVerName=ProjectIntegration
AppVersion=1.0
DefaultDirName={localappdata}\ProjectIntegration
DefaultGroupName=ProjectIntegration
DisableReadyPage=Yes
DisableDirPage=Yes
DisableWelcomePage=Yes
DisableStartupPrompt=Yes
DisableProgramGroupPage=Yes
MinVersion=0,5.1
OutputBaseFilename=ProjectIntegration
Compression=lzma
SolidCompression=yes
PrivilegesRequired=lowest
SetupIconFile=ProjectIntegration\ProjectIntegration.ico
UninstallDisplayIcon={app}\ProjectIntegration.ico
UninstallDisplayName=Uninstall
WizardImageStretch=Yes
WizardSmallImageFile=ProjectIntegration-setup-icon.bmp

[Languages]
Name: "english"; MessagesFile: "compiler:Default.isl"
Name: "brazilianportuguese"; MessagesFile: "compiler:Languages\BrazilianPortuguese.isl"

[Tasks]
Name: "desktopicon"; Description: "{cm:CreateDesktopIcon}"; GroupDescription: "{cm:AdditionalIcons}"; Flags: unchecked

[Files]
Source: "ProjectIntegration\ProjectIntegration.exe"; DestDir: "{app}"; Flags: ignoreversion
Source: "ProjectIntegration\*"; DestDir: "{app}"; Flags: ignoreversion recursesubdirs createallsubdirs

[Icons]
Name: "{group}\ProjectIntegration"; Filename: "{app}\ProjectIntegration.exe"; WorkingDir: "{app}"; IconFilename: "{app}\ProjectIntegration.ico"
Name: "{commondesktop}\ProjectIntegration"; Filename: "{app}\ProjectIntegration.exe"; WorkingDir: "{app}"; IconFilename: "{app}\ProjectIntegration.ico"; Check: returnTrue()
Name: "{commondesktop}\ProjectIntegration"; Filename: "{app}\ProjectIntegration"; Tasks: desktopicon

[Run]
Filename: "{app}\ProjectIntegration.exe"; Description: "{cm:LaunchProgram,ProjectIntegration}"; Flags: nowait postinstall skipifsilent; Check: returnTrue()
Filename: "{app}\ProjectIntegration.exe"; Parameters: "-install -svcName ""ProjectIntegration"" -svcDesc ""ProjectIntegration"" -mainExe ""ProjectIntegration.exe"" "; Check: returnFalse()

[UninstallRun]
Filename: "{app}\ProjectIntegration.exe"; Parameters: "-uninstall"; Flags: runhidden

[Code]
function returnTrue(): Boolean;
begin
  Result := True;
end;

function returnFalse(): Boolean;
begin
  Result := False;
end;

function InitializeSetup(): Boolean;
begin
  Result := True;
end;