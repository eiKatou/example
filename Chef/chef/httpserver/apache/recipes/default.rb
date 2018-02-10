#
# Cookbook Name:: apache
# Recipe:: default
#
# Copyright 2018, YOUR_COMPANY_NAME
#
# All rights reserved - Do Not Redistribute
#

log "Hello, Chef!"

#d install
apt_package 'git' do
  action :install
end

%w{
  ssh
  apache2 
}.each do |pkg|
  package "#{pkg}" do
    action :install
  end
end

# git clone

# deploy
cookbook_file "/var/www/html/index.php" do
  source "index.php"
  group "root"
  owner "root"
  mode "0644"
end

# service
service "apache2" do
  action [ :enable, :start ]
end
service "ssh" do
  action [ :enable, :start ]
end

